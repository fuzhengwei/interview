package org.itstack.interview;

//this class is used to bind user info to ThreadID, when the user logon 
import java.util.Date;
public class BeforeAdvice implements IBeforeAdvice {

	//transfer uid to TMon agent
	//this method will be captured in TMon
	public void beforeAdvice(int uid) {
		System.out.println("BeforeAdvice get a uid:"+uid);

	}
	//bind user info to ThreadID for each demo
	public void bindContext(String name,int type){
		
                UserInfo userInfo=new UserInfo();
                userInfo.name=name;
                userInfo.uid=name.hashCode();//Storage.getIndex();
                userInfo.inCallTime=new Date(new Date().getTime());
                boolean isAdd=true;
                if(type==1) {
                    for(int i=0;i<Storage.getA().size();i++) {
                        if(Storage.getA().get(i).getName().compareTo(name)==0) {
                            Storage.getA().get(i).setInCallTime(new Date(new Date().getTime()));
                            isAdd=false;
                        }
                    }
                    if(isAdd)
                    {
                        Storage.getA().add(userInfo);
                    }
                }
                if(type==2) {
                    for(int i=0;i<Storage.getB().size();i++) {
                        if(Storage.getB().get(i).getName().compareTo(name)==0) {
                            Storage.getB().get(i).setInCallTime(new Date(new Date().getTime()));
                            isAdd=false;
                        }
                    }
                    if(isAdd)
                    {
                        Storage.getB().add(userInfo);
                    }
                }
                if(type==3) {
                    for(int i=0;i<Storage.getC().size();i++) {
                        if(Storage.getC().get(i).getName().compareTo(name)==0) {
                            Storage.getC().get(i).setInCallTime(new Date(new Date().getTime()));
                            isAdd=false;
                        }
                    }
                    if(isAdd)
                    {
                        Storage.getC().add(userInfo);
                    }
                }
                beforeAdvice(userInfo.uid);
	}

}
