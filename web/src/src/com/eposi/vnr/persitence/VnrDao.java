package com.eposi.vnr.persitence;

import com.eposi.common.persitence.DaoUtil;
import com.eposi.common.persitence.HibernateUtil;
import com.eposi.common.util.AbstractBean;
import com.eposi.vnr.model.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class VnrDao extends AbstractBean {
    private static final long serialVersionUID = 2006767042505826196L;

    private DaoUtil  beanDaoUtil;
    private HibernateUtil beanHibernateUtil;

    public void init() {
        beanDaoUtil = (DaoUtil) this.getBean("beanDaoUtil");
        beanHibernateUtil=(HibernateUtil)this.getBean("beanHibernateUtil");
    }

    public void shutdown() {

    }

    /*
        Station
     */

    public List<Station> getStations() {
        return (List<Station>) beanDaoUtil.list(Station.class.getName());
    }

    public List<Station> getListStations(){
        List<Station> items=null;
        Session session=beanHibernateUtil.getSession();
        try{
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Station.class);
            criteria.addOrder(Order.asc("name"));

            items = criteria.list();
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            beanHibernateUtil.closeSession(session);
        }

        return items;
    }

    //get
    public Station getStation(String id) {
        return (Station) beanDaoUtil.get(Station.class, id);
    }

    // edit
    public Station saveStation(Station item) throws Exception {
        Station savedObject = (Station) beanDaoUtil.saveOrUpdate(item);
        return savedObject;
    }

    /*Workbench*/
    public List<Workbench> listWorkbenchs(){
        return (List<Workbench>)beanDaoUtil.list(Workbench.class.getName());
    }

    public Workbench getWorkbenchById(int id){
        return (Workbench)beanDaoUtil.get(Workbench.class,id);
    }

    public Workbench saveWorkbench(Workbench item){
        return (Workbench)beanDaoUtil.saveOrUpdate(item);
    }



    /*
       Stage
    */
    public List<Stage> getStages() {
//        return (List<Stage>) beanDaoUtil.list(Stage.class.getName());

        List<Stage> items = null;
        Session session = beanHibernateUtil.getSession();
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Stage.class);
            criteria.addOrder(Order.asc("stt"));

            items = criteria.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            beanHibernateUtil.closeSession(session);
        }

        return items;
    }

    public List<Stage> getStagesByWorkbench(int workbenchId) {

        Workbench workbench=getWorkbenchById(workbenchId);
        List<Stage> items = null;
        Session session = beanHibernateUtil.getSession();
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Stage.class);
            criteria.add(Restrictions.eq("workbench", workbench));
            criteria.addOrder(Order.asc("stt"));

            items = criteria.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            beanHibernateUtil.closeSession(session);
        }
        return items;
    }
    //get
    public Stage getStage(String id) {
        return (Stage) beanDaoUtil.get(Stage.class, id);
    }

    public Stage getStageByName(String name){
        List<Criterion> criterions=new ArrayList<Criterion>();
        criterions.add(Restrictions.eq("name",name));
        List<Stage> trains=(List<Stage>)beanDaoUtil.query(Stage.class,criterions);
        if(trains.size()>0){
            return trains.get(0);
        }
        return null;
    }

    // edit
    public Stage saveStage(Stage item) throws Exception {
        Stage savedObject = (Stage) beanDaoUtil.saveOrUpdate(item);
        return savedObject;
    }

     /*
        TrainReference
     */
    public List<TrainReference> getTrainReferences() {

        return (List<TrainReference>) beanDaoUtil.list(TrainReference.class.getName());
    }
    //get
    public TrainReference getTrainReference(String id) {
        return (TrainReference) beanDaoUtil.get(TrainReference.class, id);
    }

    public TrainReference getTrainReferenceByName(String name){
        List<Criterion> criterions=new ArrayList<Criterion>();
        criterions.add(Restrictions.eq("name",name));
        List<TrainReference> trainReferences =(List<TrainReference>)beanDaoUtil.query(TrainReference.class,criterions);
        if(trainReferences.size()>0){
            return trainReferences.get(0);
        }
        return null;
    }
//get many trainreference
/*
    public List<?> getTrainReferencesByListId(List<String> items) {
        HibernateUtil beanHibernateUtil = (HibernateUtil) getBean("beanHibernateUtil");

        Session session = beanHibernateUtil.getSession();
        session.getTransaction().begin();
        try {
            for (String item : items) {
                session.get(TrainReference.class,item);
            }
            session.flush();
            session.clear();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            beanHibernateUtil.closeSession(session);
        }

        return items;
    }
*/


    // edit
    public TrainReference saveTrainReference(TrainReference item) throws Exception {
        TrainReference savedObject = (TrainReference) beanDaoUtil.saveOrUpdate(item);
        return savedObject;
    }
    public TrainReference deleteTrainReference(TrainReference item) throws Exception {
        TrainReference savedObject = (TrainReference) beanDaoUtil.delete(item);
        return savedObject;
    }


    //list
    public List<StopReference> getStopReferences(){
        return (List<StopReference>)beanDaoUtil.list(StopReference.class.getName());
    }

    public List<StopReference> getStopReferenceListByTrainReference(TrainReference trainReference) {
        List<StopReference> items = null;
        Session session = beanHibernateUtil.getSession();
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(StopReference.class);
            criteria.add(Restrictions.eq("trainReference", trainReference));
            criteria.addOrder(Order.asc("arrivalTime"));
            items = criteria.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            beanHibernateUtil.closeSession(session);
        }

        return items;
    }

//    public List<StopReference> findStopReferenceByTrainReference(TrainReference trainReference) {
//
//        List<Criterion> criterions = new ArrayList<Criterion>();
//        criterions.add(Restrictions.eq("trainReference", trainReference));
//
//        return (List<StopReference>) beanDaoUtil.query(StopReference.class, criterions);
//    }

    public List<StopReference> findStopReferenceByState(Stage stage) {

        List<Criterion> criterions = new ArrayList<Criterion>();
        criterions.add(Restrictions.eq("stage", stage));

        return (List<StopReference>) beanDaoUtil.query(StopReference.class, criterions);
    }

    //get
    public StopReference getPlan(long id) {
        return (StopReference) beanDaoUtil.get(StopReference.class, id);
    }

    // edit
    public StopReference savePlan(StopReference item) throws Exception {
        StopReference savedObject = (StopReference) beanDaoUtil.saveOrUpdate(item);
        return savedObject;
    }

    /*
        Train
     */
    public List<Train> getTrains() {
        return (List<Train>) beanDaoUtil.list(Train.class.getName());
    }


   public List<Train> getTrainsByTime(Date time){
       List<Criterion> criterions= new ArrayList<Criterion>();
       criterions.add(Restrictions.gt("beginTime",time));

       return (List<Train>)beanDaoUtil.query(Train.class,criterions);
   }
    //get
    public Train getTrain(long id) {
        return (Train) beanDaoUtil.get(Train.class, id);
    }

    public Train getTrainByName(String name){
        List<Criterion> criterions=new ArrayList<Criterion>();
        criterions.add(Restrictions.eq("name",name));
        List<Train> trains=(List<Train>)beanDaoUtil.query(Train.class,criterions);
        if(trains.size()>0){
            return trains.get(0);
        }
        return null;
    }

    public Train getTrainByInfor(TrainReference trainReference,Date beginTime){
        List<Criterion> criterions=new ArrayList<Criterion>();
        criterions.add(Restrictions.eq("trainReference",trainReference));
        criterions.add(Restrictions.eq("beginTime",beginTime));
        List<Train> trains=(List<Train>)beanDaoUtil.query(Train.class,criterions);
        if(trains==null){
            return null;
        }else if(trains.size()>0){
            return trains.get(0);
        }
        return null;
    }

    public List<Train> findTrainsByTrainReference(TrainReference trainReference) {

        List<Criterion> criterions = new ArrayList<Criterion>();
        criterions.add(Restrictions.eq("trainReference", trainReference));

        return (List<Train>) beanDaoUtil.query(Train.class, criterions);
    }

    // edit
    public Train saveTrain(Train item) throws Exception {
        Train savedObject = (Train) beanDaoUtil.saveOrUpdate(item);
        return savedObject;
    }
    public List<?> saveOrUpdateTrains(List<Train> items) {

        Session session = beanHibernateUtil.getSession();
        session.getTransaction().begin();
        try {
            for (Train item : items) {
                session.saveOrUpdate(item);
            }
            session.flush();
            session.clear();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            beanHibernateUtil.closeSession(session);
        }

        return items;
    }

    //delete
    public Train deleteTrain(Train item) throws Exception{
        return (Train)beanDaoUtil.delete(item);
    }

    public List<?> deleteTrains(List<Train> items) {

        Session session = beanHibernateUtil.getSession();
        session.getTransaction().begin();
        try {
            for (Train item : items) {
                session.delete(item);
            }

            session.flush();
            session.clear();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            beanHibernateUtil.closeSession(session);
        }

        return items;
    }

    public Train addTrain(Train item) {
        try {
            beanDaoUtil.add(item);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return item;
    }


     /*
        StationStage
     */

    public List<StationStage> listStationStage() {
        List<StationStage> list=(List<StationStage>) beanDaoUtil.list(StationStage.class.getName());
        return list;
    }


    public List<StationStage> getStationStageListByStage(Stage stage) {
        List<StationStage> items = null;
        Session session = beanHibernateUtil.getSession();
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(StationStage.class);
            criteria.add(Restrictions.eq("stage", stage));
           /* if(stage.isArrangement()){
                criteria.addOrder(Order.asc("distance"));

            }else{
                criteria.addOrder(Order.desc("distance"));
            }*/
            criteria.addOrder(Order.asc("distance"));
            items = criteria.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            beanHibernateUtil.closeSession(session);
        }

        return items;
    }

    public StationStage getStationStage(String id){
        return (StationStage)beanDaoUtil.get(StationStage.class,id);
    }

    public List<StationStage> findByStation(Station station){
        List<Criterion> criterions=new ArrayList<Criterion>();
        criterions.add(Restrictions.eq("station",station));
        return (List<StationStage>) beanDaoUtil.query(StationStage.class,criterions);
    }

    public List<StationStage> findByStage(Stage stage){
        List<Criterion> criterions=new ArrayList<Criterion>();
        criterions.add(Restrictions.eq("Stage",stage));
        return (List<StationStage>) beanDaoUtil.query(StationStage.class,criterions);
    }

    public StationStage saveStationStage(StationStage item) throws Exception {
        StationStage savedObject = (StationStage) beanDaoUtil.saveOrUpdate(item);
        return savedObject;
    }

    /***********************************************************************************
     * STOP
     */

    public List<Stop> findStopsByStageBetween(Stage stage, Date fromDate, Date toDate) {
        List<StationStage> stationStages = this.getStationStageListByStage(stage);

        List<Stop> items = null;
        Session session = beanHibernateUtil.getSession();
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Stop.class);
            criteria.add(Restrictions.between("arrivalTime", fromDate.getTime(), toDate.getTime()));
            criteria.add(Restrictions.in("stationStage", stationStages));

            criteria.addOrder(Order.asc("arrivalTime"));
            items = criteria.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            beanHibernateUtil.closeSession(session);
        }

        return items;
    }
    public List<Stop> findStopByTrainAndListStationStage(Train train,int workbenchId) {
        List<Stage> stages=this.getStagesByWorkbench(workbenchId);
        List<StationStage> stationStageList= new ArrayList<StationStage>();
        for(Stage st:stages){
            List<StationStage> itemStations=this.getStationStageListByStage(st);
            for(StationStage ss:itemStations){
                stationStageList.add(ss);
            }
        }
        List<Stop> items = null;
        Session session = beanHibernateUtil.getSession();
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Stop.class);
            criteria.add(Restrictions.eq("train", train));
            criteria.add(Restrictions.in("stationStage", stationStageList));
            criteria.addOrder(Order.asc("arrivalTime"));
            items = criteria.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            beanHibernateUtil.closeSession(session);
        }

        return items;

    }
    public List<Stop> findStopByTrain(Train train) {
        List<Stop> items = null;
        Session session = beanHibernateUtil.getSession();
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Stop.class);
            criteria.add(Restrictions.eq("train", train));

            criteria.addOrder(Order.asc("arrivalTime"));
            items = criteria.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            beanHibernateUtil.closeSession(session);
        }

        return items;

    }
    public List<Stop> findStopByTrainList(List<Train> trains) {
        List<Stop> items = null;
        Session session = beanHibernateUtil.getSession();
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Stop.class);
            criteria.add(Restrictions.in("train", trains));
            criteria.addOrder(Order.asc("arrivalTime"));
            items = criteria.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            beanHibernateUtil.closeSession(session);
        }

        return items;

    }

    public List<Stop> findStopByTrainAndStationStage(Train train,StationStage stationStage) {

        List<Criterion> criterions = new ArrayList<Criterion>();
        criterions.add(Restrictions.eq("train", train));
        criterions.add(Restrictions.eq("stationStage", stationStage));

        return (List<Stop>) beanDaoUtil.query(Stop.class, criterions);
    }

    public List<Stop> findStopByTrain(Train train, Date fromDate, Date toDate, boolean isLocked) {

        List<Criterion> criterions = new ArrayList<Criterion>();
        criterions.add(Restrictions.eq("train", train));
        criterions.add(Restrictions.between("arrivalTime", fromDate, toDate));
        criterions.add(Restrictions.eq("locked", isLocked));

        return (List<Stop>) beanDaoUtil.query(Stop.class, criterions);
    }


    public List<Stop> findStopByStage(Stage stage) {

        List<Criterion> criterions = new ArrayList<Criterion>();
        criterions.add(Restrictions.eq("stage", stage));

        return (List<Stop>) beanDaoUtil.query(Stop.class, criterions);
    }

    public List<Stop> findStopByStage(Stage stage, Date fromDate, Date toDate) {
        List<StationStage> stationStages = this.getStationStageListByStage(stage);

        List<Stop> items = null;
        Session session = beanHibernateUtil.getSession();
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Stop.class);
            criteria.add(Restrictions.between("arrivalTime", fromDate.getTime(), toDate.getTime()));
            criteria.add(Restrictions.in("stationStage", stationStages));
            criteria.addOrder(Order.asc("arrivalTime"));
            items = criteria.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            beanHibernateUtil.closeSession(session);
        }

        return items;

    }

    //get
    public Stop getStop(long id) {
        return (Stop) beanDaoUtil.get(Stop.class, id);
    }

    //get all stop
    public List<Stop> getListStopAll(Date time){
        List<Stop> items = null;
        Session session = beanHibernateUtil.getSession();
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Stop.class);
            criteria.add(Restrictions.gt("arrivalTime",time.getTime()));
            criteria.addOrder(Order.asc("arrivalTime"));
            items = criteria.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            beanHibernateUtil.closeSession(session);
        }

        return items;
    }

    //get stoplist time after
    public List<Stop> getListStopByMoreTime(Date time){
        List<Stop> items = null;
        Session session = beanHibernateUtil.getSession();
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Stop.class);
            criteria.add(Restrictions.gt("arrivalTime",time.getTime()-2*60*60000));//lay du lieu tu 16h hom trc toi 18h sau tranh luc giao ke hoạch 18h bi thieu 1 stop
            criteria.addOrder(Order.asc("arrivalTime"));//de chuan thi phai ket hop dc ca du lieu arrivalTime va departureTime cong lai moi het dc cac truong hop
            items = criteria.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            beanHibernateUtil.closeSession(session);
        }

        return items;
    }
    public List<Stop> getListStopByDepartureMoreTime(Date time){
        List<Stop> items = null;
        Session session = beanHibernateUtil.getSession();
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Stop.class);
            criteria.add(Restrictions.gt("departureTime",time.getTime()-2*60*60000));//lay du lieu tu 16h hom trc toi 18h sau tranh luc giao ke hoạch 18h bi thieu 1 stop
            criteria.addOrder(Order.asc("departureTime"));//de chuan thi phai ket hop dc ca du lieu arrivalTime va departureTime cong lai moi het dc cac truong hop
            items = criteria.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            beanHibernateUtil.closeSession(session);
        }

        return items;
    }

    public List<Stop> getListStopInDate(Date time){
        List<Stop> items = null;
        Session session = beanHibernateUtil.getSession();
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Stop.class);
            criteria.add(Restrictions.between("arrivalTime", time.getTime()-2*60*60000, time.getTime()+86400000));//lay du lieu tu 16h hom trc toi 18h sau tranh' luc giao ke hoạch 18h bi thieu 1 stop
            criteria.addOrder(Order.asc("arrivalTime"));
            items = criteria.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            beanHibernateUtil.closeSession(session);
        }

        return items;
    }

    public List<Stop> getListStopInDateByDepartureTime(Date time){
        List<Stop> items = null;
        Session session = beanHibernateUtil.getSession();
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Stop.class);
            criteria.add(Restrictions.between("departureTime", time.getTime()-2*60*60000, time.getTime()+86400000));//lay du lieu tu 16h hom trc toi 18h sau tranh' luc giao ke hoạch 18h bi thieu 1 stop
            criteria.addOrder(Order.asc("departureTime"));
            items = criteria.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            beanHibernateUtil.closeSession(session);
        }

        return items;
    }

    // edit
    public Stop saveStop(Stop item) throws Exception {
        Stop savedObject = (Stop) beanDaoUtil.saveOrUpdate(item);
        return savedObject;
    }
    public Stop deleteStop(Stop item ) throws  Exception{
        return (Stop)beanDaoUtil.delete(item);
    }
    //Save many stops
    public List<?> saveOrUpdateStops(List<Stop> items) {

        Session session = beanHibernateUtil.getSession();
        session.getTransaction().begin();
        try {
            for (Stop stop : items) {
                session.saveOrUpdate(stop);
            }
            session.flush();
            session.clear();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            beanHibernateUtil.closeSession(session);
        }

        return items;
    }
    //delete many stop
    public List<?> deleteStops(List<Stop> items) {

        Session session = beanHibernateUtil.getSession();
        session.getTransaction().begin();
        try {
            for (Stop stop : items) {
                session.delete(stop);
            }

            session.flush();
            session.clear();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            beanHibernateUtil.closeSession(session);
        }

        return items;
    }


    public Stop addStop(Stop item) {
        try {
            beanDaoUtil.add(item);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return item;
    }

    /*
        StopReference
     */
    public StopReference getStopReferenceByTrainAndStationStage(TrainReference trainReference,StationStage stationStage){
        List<Criterion> criterions = new ArrayList<Criterion>();
        criterions.add(Restrictions.eq("trainReference", trainReference));
        criterions.add(Restrictions.eq("stationStage", stationStage));

        return (StopReference) beanDaoUtil.query(StopReference.class, criterions);
    }
    public List<StopReference> getStopReferenceByTrainAndStation(TrainReference trainReference,Station station){
        List<StationStage> items=findByStation(station);
        List<Criterion> criterions = new ArrayList<Criterion>();
        criterions.add(Restrictions.eq("trainReference", trainReference));
        criterions.add(Restrictions.in("stationStage", items));

        return (List<StopReference>) beanDaoUtil.query(StopReference.class, criterions);
    }
    public StopReference saveStopReference(StopReference item){
        StopReference savedObject = (StopReference) beanDaoUtil.saveOrUpdate(item);
        return savedObject;
    }
    //edit list stopreferences
    public List<?> editListStopReferences(List<StopReference> items) {

        Session session = beanHibernateUtil.getSession();
        session.getTransaction().begin();
        try {
            for (StopReference item : items) {
                session.saveOrUpdate(item);
            }
            session.flush();
            session.clear();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            beanHibernateUtil.closeSession(session);
        }

        return items;
    }
    //delete list stopreferences
    public List<?> deleteListStopReferences(List<StopReference> items) {

        Session session = beanHibernateUtil.getSession();
        session.getTransaction().begin();
        try {
            for (StopReference item : items) {
                session.delete(item);
            }
            session.flush();
            session.clear();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            beanHibernateUtil.closeSession(session);
        }

        return items;
    }
    public StopReference getStopReference(long id){
        return (StopReference)beanDaoUtil.get(StopReference.class,id);
    }

}
