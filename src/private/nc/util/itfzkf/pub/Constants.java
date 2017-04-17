package nc.util.itfzkf.pub;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import nc.vo.ecn.eco.util.EcoUtil;

import org.apache.commons.lang.StringUtils;

/**
 * 接口常量类
 *
 */
public class Constants {
	
	//配置文件最后修改时间
	private static long lastModifiedTimeCache = 0l;
	//读取constant.properties中的数据
	private static Properties constantProp;
	
	/** 工程模块名*/
	public final static String MODULE_NAME = "itfzkf"; //工程模块名
	
	private static final ZkungfuLogger log = new ZkungfuLogger("Contants");
	
	private static void init() throws IOException {
		File constantConf = new File(Constants.class.getResource("/itfzkf/constant.properties").getPath());
		//首次或配置文件发生变化则进行加载
		if (constantProp == null || isFileChanged(constantConf)) {
			synchronized (EcoUtil.class) {
				if (constantProp == null || isFileChanged(constantConf)) {
					constantProp = new Properties();
					constantProp.load(Constants.class.getResourceAsStream("/itfzkf/constant.properties"));
					//更新配置文件最后修改时间
					lastModifiedTimeCache = constantConf.lastModified();
				}
			}
		}
	}
	
	/**
	 * 判断配置文件是否有更新
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
	 * 根据key从constants.properties中取值
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
