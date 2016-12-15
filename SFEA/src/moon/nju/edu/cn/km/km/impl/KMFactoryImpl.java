/**
 */
package moon.nju.edu.cn.km.km.impl;

import moon.nju.edu.cn.km.km.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class KMFactoryImpl extends EFactoryImpl implements KMFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static KMFactory init() {
		try {
			KMFactory theKMFactory = (KMFactory)EPackage.Registry.INSTANCE.getEFactory(KMPackage.eNS_URI);
			if (theKMFactory != null) {
				return theKMFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new KMFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KMFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case KMPackage.KNOWLEDGE_MODEL: return createKnowledgeModel();
			case KMPackage.CONCEPT: return createConcept();
			case KMPackage.COUNTABLE_ELEMENT: return createCountableElement();
			case KMPackage.QUANTIFIABLE_ELEMENT: return createQuantifiableElement();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KnowledgeModel createKnowledgeModel() {
		KnowledgeModelImpl knowledgeModel = new KnowledgeModelImpl();
		return knowledgeModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Concept createConcept() {
		ConceptImpl concept = new ConceptImpl();
		return concept;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CountableElement createCountableElement() {
		CountableElementImpl countableElement = new CountableElementImpl();
		return countableElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QuantifiableElement createQuantifiableElement() {
		QuantifiableElementImpl quantifiableElement = new QuantifiableElementImpl();
		return quantifiableElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KMPackage getKMPackage() {
		return (KMPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static KMPackage getPackage() {
		return KMPackage.eINSTANCE;
	}

} //KMFactoryImpl
