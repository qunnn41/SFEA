/**
 */
package moon.nju.edu.cn.fm.model.impl;

import moon.nju.edu.cn.fm.model.*;

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
public class SFEAFactoryImpl extends EFactoryImpl implements SFEAFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SFEAFactory init() {
		try {
			SFEAFactory theSFEAFactory = (SFEAFactory)EPackage.Registry.INSTANCE.getEFactory(SFEAPackage.eNS_URI);
			if (theSFEAFactory != null) {
				return theSFEAFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SFEAFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SFEAFactoryImpl() {
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
			case SFEAPackage.FEATURE_MODEL: return createFeatureModel();
			case SFEAPackage.FEATURE: return createFeature();
			case SFEAPackage.OR_FEATURE: return createOrFeature();
			case SFEAPackage.XOR_FEATURE: return createXorFeature();
			case SFEAPackage.CARDINALITY: return createCardinality();
			case SFEAPackage.FEATURE_CARDINALITY: return createFeatureCardinality();
			case SFEAPackage.GROUP_CARDINALITY: return createGroupCardinality();
			case SFEAPackage.ATTRIBUTE: return createAttribute();
			case SFEAPackage.CONSTRAINTS: return createConstraints();
			case SFEAPackage.BOOLEAN_CONSTRAINT: return createBooleanConstraint();
			case SFEAPackage.IMPLIES_CONSTRAINTS: return createImpliesConstraints();
			case SFEAPackage.EXCLUDES: return createExcludes();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureModel createFeatureModel() {
		FeatureModelImpl featureModel = new FeatureModelImpl();
		return featureModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Feature createFeature() {
		FeatureImpl feature = new FeatureImpl();
		return feature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrFeature createOrFeature() {
		OrFeatureImpl orFeature = new OrFeatureImpl();
		return orFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XorFeature createXorFeature() {
		XorFeatureImpl xorFeature = new XorFeatureImpl();
		return xorFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Cardinality createCardinality() {
		CardinalityImpl cardinality = new CardinalityImpl();
		return cardinality;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureCardinality createFeatureCardinality() {
		FeatureCardinalityImpl featureCardinality = new FeatureCardinalityImpl();
		return featureCardinality;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GroupCardinality createGroupCardinality() {
		GroupCardinalityImpl groupCardinality = new GroupCardinalityImpl();
		return groupCardinality;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Attribute createAttribute() {
		AttributeImpl attribute = new AttributeImpl();
		return attribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Constraints createConstraints() {
		ConstraintsImpl constraints = new ConstraintsImpl();
		return constraints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanConstraint createBooleanConstraint() {
		BooleanConstraintImpl booleanConstraint = new BooleanConstraintImpl();
		return booleanConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImpliesConstraints createImpliesConstraints() {
		ImpliesConstraintsImpl impliesConstraints = new ImpliesConstraintsImpl();
		return impliesConstraints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Excludes createExcludes() {
		ExcludesImpl excludes = new ExcludesImpl();
		return excludes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SFEAPackage getSFEAPackage() {
		return (SFEAPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static SFEAPackage getPackage() {
		return SFEAPackage.eINSTANCE;
	}

} //SFEAFactoryImpl
