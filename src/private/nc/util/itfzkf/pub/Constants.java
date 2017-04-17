package nc.util.itfzkf.pub;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import nc.vo.ecn.eco.util.EcoUtil;

import org.apache.commons.lang.StringUtils;

/**
 * �ӿڳ�����
 *
 */
public class Constants {
	
	//�����ļ�����޸�ʱ��
	private static long lastModifiedTimeCache = 0l;
	//��ȡconstant.properties�е�����
	private static Properties constantProp;
	
	/** ����ģ����*/
	public final static String MODULE_NAME = "itfzkf"; //����ģ����
	
	private static final ZkungfuLogger log = new ZkungfuLogger("Contants");
	
	private static void init() throws IOException {
		File constantConf = new File(Constants.class.getResource("/itfzkf/constant.properties").getPath());
		//�״λ������ļ������仯����м���
		if (constantProp == null || isFileChanged(constantConf)) {
			synchronized (EcoUtil.class) {
				if (constantProp == null || isFileChanged(constantConf)) {
					constantProp = new Properties();
					constantProp.load(Constants.class.getResourceAsStream("/itfzkf/constant.properties"));
					//���������ļ�����޸�ʱ��
					lastModifiedTimeCache = constantConf.lastModified();
				}
			}
		}
	}
	
	/**
	 * �ж������ļ��Ƿ��и���
	 * @param file
	 * @return
	 */
	private static boolean isFileChanged(File file) {
		long lastModifiedTime = file.lastModified();
		if (lastModifiedTime != lastModifiedTimeCache) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * ����key��constants.properties��ȡֵ
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		try {
			init();
		} catch (IOException e) {
			log.error("Fail to load constant.properties", e);
		}
		if (!StringUtils.isEmpty(key)) {
			return (String) constantProp.get(key);
		} else {
			return "";
		}
		
	}

}
