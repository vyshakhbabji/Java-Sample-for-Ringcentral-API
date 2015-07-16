import java.util.concurrent.TimeUnit;


public class MainClass {

	
	public static void main(String[] args) throws Exception {
		OAuth o = new OAuth();
		String access_token = o.OAuthorizer();
		
		RingOut r = new RingOut();
		String ringOutId =  r.ringOut(access_token);
		//TimeUnit.SECONDS.sleep(10);
		//r.deleteRingOut(access_token, ringOutId);
//		
//		CallLog c = new CallLog();
//		c.callLog(access_token);
//		
		
		
	}
}
