import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;



public class Runner {
	public static void main(String[] args) throws Exception {
		OAuth o = new OAuth();
		final String access_token = o.OAuthorizer();
		final CallLog c  = new CallLog();
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

		Runnable toRun = new Runnable() {
			public void run() {
				try {
					RingOut r = new RingOut();
					String ringOutId =  r.ringOut(access_token);
					TimeUnit.SECONDS.sleep(10);
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
