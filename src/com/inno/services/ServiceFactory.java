package com.inno.services;

/**
 * Created by Administrator on 2017/6/20.
 */
public class ServiceFactory {
    private static class ServiceFactoryHolder {
        private static final ServiceFactory INSTANCE = new ServiceFactory();
    }
    private ServiceFactory(){}
    public static final ServiceFactory getInstance() {
        return ServiceFactoryHolder.INSTANCE;
    }


    public com.inno.services.DauService getDauService(String ActionClassName){
        DauService action = null;
        try{
            action = (DauService) Class.forName(ActionClassName).newInstance();
        }catch(Exception e){
            e.printStackTrace();
        }
        return action;
    }


    public com.inno.services.InsertService getInsertService(String ActionClassName){
        InsertService action = null;
        try{
            action = (InsertService) Class.forName(ActionClassName).newInstance();
        }catch(Exception e){
            e.printStackTrace();
        }
        return action;
    }


    public com.inno.services.MonService getMonService(String ActionClassName){
        MonService action = null;
        try{
            action = (MonService) Class.forName(ActionClassName).newInstance();
        }catch(Exception e){
            e.printStackTrace();
        }
        return action;
    }


    public com.inno.services.InvestService getInvestService(String ActionClassName){
        InvestService action = null;
        try{
            action = (InvestService) Class.forName(ActionClassName).newInstance();
        }catch(Exception e){
            e.printStackTrace();
        }
        return action;
    }

    public TycService getTycService(String ActionClassName){
        TycService action = null;
        try{
            action = (TycService) Class.forName(ActionClassName).newInstance();
        }catch(Exception e){
            e.printStackTrace();
        }
        return action;
    }
}
