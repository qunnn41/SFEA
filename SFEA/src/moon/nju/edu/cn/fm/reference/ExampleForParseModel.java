package moon.nju.edu.cn.fm.reference;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import moon.nju.edu.cn.fm.model.BooleanConstraints;
import moon.nju.edu.cn.fm.model.CardExConstraint;
import moon.nju.edu.cn.fm.model.Constraints;
import moon.nju.edu.cn.fm.model.Feature;
import moon.nju.edu.cn.fm.model.FeatureModel;
import moon.nju.edu.cn.fm.model.Operation;
import moon.nju.edu.cn.fm.model.OrFeature;
import moon.nju.edu.cn.fm.model.SFEAPackage;
import moon.nju.edu.cn.fm.model.XorFeature;

/**
 * How to parse a feature model
 * @author wyq
 */

public class ExampleForParseModel {
	private FeatureModel cloudFeatureModel;
	private Feature rootFeature;
	private LinkedList<Constraints> constraints = new LinkedList<Constraints>();
	private LinkedList<Feature> queue = new LinkedList<Feature>();
	
	public ExampleForParseModel() {
		SFEAPackage.eINSTANCE.eClass();
		Resource.Factory.Registry registry = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> map = registry.getExtensionToFactoryMap();
		map.put("fm", new XMIResourceFactoryImpl());
		
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resource = resourceSet.getResource(URI.createURI("feature_model/heroku.fm"), true);
		this.cloudFeatureModel = (FeatureModel) resource.getContents().get(0);
		this.rootFeature = cloudFeatureModel.getRoot();
		this.constraints.addAll(cloudFeatureModel.getConstraints());
		queue.add(rootFeature);
		
		while (!queue.isEmpty()) {
			Feature feature = queue.poll();
			System.out.println("Feature Name: " + feature.getName());
			if (feature instanceof XorFeature) {
				XorFeature xorFeature = (XorFeature) feature;
				System.out.println("=========================XorFeature\n" + xorFeature.getName());
				for (Feature subFeature: xorFeature.getVariants()) {
					System.out.println(subFeature.getName());
					queue.add(subFeature);
				}
				
				int min = xorFeature.getGroupCardinality().getMin();
				int max = xorFeature.getGroupCardinality().getMax();
				System.out.println(min + "\t" + max + "\n=============================");
			} else if (feature instanceof OrFeature) {
				OrFeature orFeature = (OrFeature) feature;
				System.out.println("=========================OrFeature\n" + orFeature.getName());
				for (Feature subFeature: orFeature.getVariants()) {
					System.out.println(subFeature.getName());
					queue.add(subFeature);
				}
				
				int min = orFeature.getGroupCardinality().getMin();
				int max = orFeature.getGroupCardinality().getMax();
				System.out.println(min + "\t" + max + "\n=============================");
			} else {
				for (Feature subFeature: feature.getSubFeatures()) {
					int min = subFeature.getFeatureCardinality().getMin();
					int max = subFeature.getFeatureCardinality().getMax();
					
					if (min == 1 && max == 1) {
						System.out.println("Mandatory: " + feature.getName() + "->" + subFeature.getName());
					} else if (min == 0 && max == 1) {
						System.out.println("Optional: " + feature.getName() + "->" + subFeature.getName());
					}
					
					queue.add(subFeature);
				}
			}
		}

		for (Constraints constraint : constraints) {
			if (constraint instanceof BooleanConstraints) {
				BooleanConstraints booleanConstraints = (BooleanConstraints) constraint;
				System.out.println(booleanConstraints.getFrom().getName() + "\t" + booleanConstraints.getTo().getName());
			} else if (constraint instanceof CardExConstraint){
				CardExConstraint cardExConstraint = (CardExConstraint) constraint;
				Operation action = cardExConstraint.getAction();
				List<Operation> condition = cardExConstraint.getCondition();
				
				for (Operation operation : condition) {
					System.out.print(operation.getValue() + operation.getFeature().getName() + "\t");
				}
				
				System.out.println(cardExConstraint.getOperator() + "\t" + action.getValue() + action.getFeature().getName());
			}
		}
	}
	
	public static void main(String[] args) {
		new ExampleForParseModel();
	}
}
