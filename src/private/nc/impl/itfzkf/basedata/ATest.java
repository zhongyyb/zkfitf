package nc.impl.itfzkf.basedata;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.itf.itfzkf.basedata.IAccassitemService;
import nc.util.itfzkf.pub.ZkungfuLogger;
import nc.vo.pub.BusinessException;

public class ATest implements IBackgroundWorkPlugin {

	private final ZkungfuLogger log = new ZkungfuLogger("ATest");
	@Override
	public PreAlertObject executeTask(BgWorkingContext arg0)
			throws BusinessException {
		
		try{
			IAccassitemService sev = (IAccassitemService) NCLocator.getInstance().lookup(IAccassitemService.class);
			sev.getSubjassFromView();
		} catch (BusinessException e) {
			log.error("BusinessException: ", e);
			throw e;
		}  catch (Exception e) {
			log.error("Exception: ", e);
		}
		return null;
	}

}
