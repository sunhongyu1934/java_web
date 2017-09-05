package com.inno.dao;

/**
 * Created by Administrator on 2017/6/20.
 */
public class DaoFactory {
    private static class DaoFactoryHolder {
        private static final DaoFactory INSTANCE = new DaoFactory();
    }
    private DaoFactory (){}
    public static final DaoFactory getInstance() {
        return DaoFactoryHolder.INSTANCE;
    }


    public DauDao getDauDao(String ActionClassName){
        DauDao action = null;
        try{
            action = (DauDao) Class.forName(ActionClassName).newInstance();
        }catch(Exception e){
            e.printStackTrace();
        }
        return action;
    }

    public InsertDao getInsertDao(String ActionClassName){
        InsertDao action = null;
        try{
            action = (InsertDao) Class.forName(ActionClassName).newInstance();
        }catch(Exception e){
            e.printStackTrace();
        }
        return action;
    }


    public MonDao getMonDao(String ActionClassName){
        MonDao action = null;
        try{
            action = (MonDao) Class.forName(ActionClassName).newInstance();
        }catch(Exception e){
            e.printStackTrace();
        }
        return action;
    }

    public InvestDao getInvestDao(String ActionClassName){
        InvestDao action = null;
        try{
            action = (InvestDao) Class.forName(ActionClassName).newInstance();
        }catch(Exception e){
            e.printStackTrace();
        }
        return action;
    }

}
