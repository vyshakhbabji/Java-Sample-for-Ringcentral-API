package call_Log_Logger;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;



public class Runner {
	public static void main(String[] args) throws Exception {
		OAuth o = new OAuth();
		final String access_token = o.OAuthorizer();
		System.out.println(access_token);
		final CopyOfCallLog c  = new CopyOfCallLog();
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

		Runnable toRun = new Runnable() {
			public void run() {
				try {
					RingOut r = new RingOut();
					String ringOutId =  r.ringOut(access_token);
					TimeUnit.SECONDS.sleep(5);
					r.deleteRingOut(access_token, ringOutId);
					c.callLog(access_token);
				} catch (Exception e) {
						e.printStackTrace();
				}
			}
		};
		ScheduledFuture<?> handle = scheduler.scheduleAtFixedRate(toRun, 1, 30, TimeUnit.SECONDS); //scheduleAtFixedRate(TimerTask task,long delay,long period)
	}
}
