package nc.util.itfzkf.pub;

import nc.bs.logging.Log;

public class ZkungfuLogger {

	private static Log log = Log.getInstance(Constants.MODULE_NAME);
	
	private String logHead;
	
	public ZkungfuLogger() {}
	
	public ZkungfuLogger(String logHead) {
		this.logHead = "<" + logHead + ">:  ";
	}
	
	public void debug(String msg) {
		log.debug(logHead + msg);
	}
	
	public void info(String msg) {
		log.info(logHead + msg);
	}
	
	public void warn(String msg) {
		log.warn(logHead + msg);
	}
	
	public void error(String msg) {
		log.error(logHead + msg);
	}
	
	public void error(Throwable throwable) {
		log.error(logHead + throwable);
	}
	
	public void error(String msg, Throwable throwable) {
		log.error(logHead + msg, throwable);
	}
	
	
	
}
