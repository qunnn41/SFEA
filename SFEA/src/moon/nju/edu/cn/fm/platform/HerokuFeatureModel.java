package moon.nju.edu.cn.fm.platform;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import moon.nju.edu.cn.fm.model.Feature;
import moon.nju.edu.cn.fm.model.FeatureCardinality;
import moon.nju.edu.cn.fm.model.FeatureModel;
import moon.nju.edu.cn.fm.model.GroupCardinality;
import moon.nju.edu.cn.fm.model.OrFeature;
import moon.nju.edu.cn.fm.model.SFEAFactory;
import moon.nju.edu.cn.fm.model.SFEAPackage;

public class HerokuFeatureModel {
	private FeatureModel herokuFeatureModel;
	
	public HerokuFeatureModel(Resource resource) {
		SFEAFactory factory = SFEAFactory.eINSTANCE;
		herokuFeatureModel = factory.createFeatureModel();
		
		// Set Root Feature
		Feature herokuFeature = factory.createFeature();
		herokuFeature.setName("Heroku");
		herokuFeatureModel.setRoot(herokuFeature);
		
		Feature dynoFeature = factory.createFeature();
		dynoFeature.setName("Dyno");
		FeatureCardinality dynoCardinality = factory.createFeatureCardinality();
		dynoFeature.setFeatureCardinality(dynoCardinality);
		dynoCardinality.setMax(10);
		dynoCardinality.setMin(0);
		// TODO add dyno feature attributes
		herokuFeature.getSubFeatures().add(dynoFeature);
		resource.getContents().add(dynoFeature);
		
		OrFeature languageFeature = factory.createOrFeature();
		languageFeature.setName("Language");
		FeatureCardinality languageFeatureCardinality = factory.createFeatureCardinality();
		languageFeature.setFeatureCardinality(languageFeatureCardinality);
		languageFeatureCardinality.setMax(1);
		languageFeatureCardinality.setMin(1);
		GroupCardinality languageGroupCardinality = factory.createGroupCardinality();
		
		Feature clojureFeature = factory.createFeature();
		clojureFeature.setName("Clojure");
		languageFeature.getVariants().add(clojureFeature);
		Feature javaFeature = factory.createFeature();
		javaFeature.setName("Java");
		languageFeature.getVariants().add(javaFeature);
		Feature rubyFeature = factory.createFeature();
		rubyFeature.setName("Ruby");
		languageFeature.getVariants().add(rubyFeature);
		Feature scalaFeature = factory.createFeature();
		scalaFeature.setName("Scala");
		languageFeature.getVariants().add(scalaFeature);
		Feature pythonFeature = factory.createFeature();
		pythonFeature.setName("Python");
		languageFeature.getVariants().add(pythonFeature);
		Feature jsFeature = factory.createFeature();
		jsFeature.setName("JavaScript");
		languageFeature.getVariants().add(jsFeature);
		
		languageGroupCardinality.setMin(1);
		languageGroupCardinality.setMax(languageFeature.getVariants().size());
		herokuFeature.getSubFeatures().add(languageFeature);
		resource.getContents().add(languageFeature);
		
		Feature frameworkFeature = factory.createFeature();
		frameworkFeature.setName("Framework");
		herokuFeature.getSubFeatures().add(frameworkFeature);
		resource.getContents().add(frameworkFeature);

		Feature locationFeature = factory.createFeature();
		locationFeature.setName("Location");
		herokuFeature.getSubFeatures().add(locationFeature);
		resource.getContents().add(locationFeature);
		
		Feature serviceFeature = factory.createFeature();
		serviceFeature.setName("Service");
		herokuFeature.getSubFeatures().add(serviceFeature);
		resource.getContents().add(serviceFeature);
		
		resource.getContents().add(herokuFeature);
	}
	
	public static void main(String[] args) {
		Resource.Factory.Registry registry = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> map = registry.getExtensionToFactoryMap();
		map.put("fm", new XMIResourceFactoryImpl());
		
		ResourceSet resourceSet = new ResourceSetImpl();
		
		Resource resource = resourceSet.createResource(URI.createURI("feature_model/heroku.fm"));
		HerokuFeatureModel heroku = new HerokuFeatureModel(resource);
		resource.getContents().add(heroku.herokuFeatureModel);
		System.out.println(heroku.herokuFeatureModel.getRoot().getName());
		try {
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		SFEAPackage.eINSTANCE.eClass();
//		Resource resource = resourceSet.getResource(URI.createURI("feature_model/heroku.fm"), true);
//		FeatureModel heroku = (FeatureModel) resource.getContents().get(0);
//		for (Object obj : heroku.getRoot().getSubFeatures()) {
//			Feature feature = (Feature)obj;
//			System.out.println(feature.getName());
//			if (obj instanceof OrFeature) {
//				OrFeature orFeature = (OrFeature) obj;
//				System.out.println(orFeature.getGroupCardinality().getMax() + "\t" + orFeature.getGroupCardinality().getMin());
//			}
//		}
	}
}
