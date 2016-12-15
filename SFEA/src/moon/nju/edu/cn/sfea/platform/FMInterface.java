package moon.nju.edu.cn.sfea.platform;

public interface FMInterface {
	/**
	 * create an instance from feature selection
	 * check whether it is a valid configuration for each cloud platform
	 * @param string
	 */
	void createInstance(String[] string);
}
