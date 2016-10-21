package moon.nju.edu.cn.fm.platform;

import java.util.Map;

import moon.nju.edu.cn.fm.model.Feature;
import moon.nju.edu.cn.fm.model.FeatureModel;
import moon.nju.edu.cn.fm.model.OrFeature;
import moon.nju.edu.cn.fm.model.SFEAPackage;
import moon.nju.edu.cn.fm.model.XorFeature;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class HerokuFeatureModel {
	private FeatureModel herokuFeatureModel;
	
	public HerokuFeatureModel() {
		SFEAPackage.eINSTANCE.eClass();
		Resource.Factory.Registry registry = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> map = registry.getExtensionToFactoryMap();
		map.put("fm", new XMIResourceFactoryImpl());
		
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resource = resourceSet.getResource(URI.createURI("feature_model/heroku.fm"), true);
		this.herokuFeatureModel = (FeatureModel) resource.getContents().get(0);
		System.out.println(herokuFeatureModel.getRoot().getName());
		for (Feature f: herokuFeatureModel.getRoot().getSubFeatures()) {
			System.out.println(f.getName());
			for (Feature ff: f.getSubFeatures()) {
				System.out.println("\t" + ff.getName());
			}
			
			if (f instanceof OrFeature) {
				//OrFeature
				System.out.println("Or\t" + ((OrFeature)f).getVariants());
			} else if (f instanceof XorFeature) {
				//XorFeature
			} else {
				//Optional or Mandatory
			}
		}
	}
	
	public static void main(String[] args) {
		new HerokuFeatureModel();
	}
}
