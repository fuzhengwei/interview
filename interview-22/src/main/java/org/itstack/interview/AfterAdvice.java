package org.itstack.interview;
import java.util.*;

//this class is used to probe info from program process
public class AfterAdvice implements IAfterAdvice {

	//specify the storage of user info for each demo
    Vector<UserInfo> storage=null;
    //specify the probe result of user info for each demo
    Vector<UserInfo> probe=null;
    public AfterAdvice(Vector<UserInfo> storage,Vector<UserInfo> probe)
    {
        this.storage=storage;
        this.probe=probe;
    }
    public void afterAdvice(int uid) {
		//this method will be capture by TMon agent
		//uid is passed in by TMon agent
		//
        System.out.println("AfterAdvice get a uid: "+uid);
        System.out.println("Storage size:"+storage.size());
        //use uid to copy user info from Storage to Probe
        for(int i=0;i<storage.size();i++) {
        
            if(storage.get(i).getUid()==uid) {
                UserInfo userInfo=new UserInfo();
                userInfo.setName(storage.get(i).getName());
                userInfo.setUid(storage.get(i).getUid());
                userInfo.setInCallTime(storage.get(i).getInCallTime());
                userInfo.setOutCallTime(new Date(new Date().getTime()));
                probe.add(userInfo);
            }
        }
        System.out.println("Probe size: "+probe.size());
}
	
	public void outCallProbe(){
		afterAdvice(0);
	}

}
