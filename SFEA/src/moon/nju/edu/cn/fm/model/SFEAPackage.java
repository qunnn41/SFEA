/**
 */
package moon.nju.edu.cn.fm.model;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see moon.nju.edu.cn.fm.model.SFEAFactory
 * @model kind="package"
 * @generated
 */
public interface SFEAPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "fm";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "www.example.com/fm";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "fm";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SFEAPackage eINSTANCE = moon.nju.edu.cn.fm.model.impl.SFEAPackageImpl.init();

	/**
	 * The meta object id for the '{@link moon.nju.edu.cn.fm.model.impl.FeatureModelImpl <em>Feature Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see moon.nju.edu.cn.fm.model.impl.FeatureModelImpl
	 * @see moon.nju.edu.cn.fm.model.impl.SFEAPackageImpl#getFeatureModel()
	 * @generated
	 */
	int FEATURE_MODEL = 0;

	/**
	 * The feature id for the '<em><b>Root</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_MODEL__ROOT = 0;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_MODEL__CONSTRAINTS = 1;

	/**
	 * The number of structural features of the '<em>Feature Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_MODEL_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Feature Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_MODEL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link moon.nju.edu.cn.fm.model.impl.FeatureImpl <em>Feature</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see moon.nju.edu.cn.fm.model.impl.FeatureImpl
	 * @see moon.nju.edu.cn.fm.model.impl.SFEAPackageImpl#getFeature()
	 * @generated
	 */
	int FEATURE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Sub Features</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE__SUB_FEATURES = 1;

	/**
	 * The feature id for the '<em><b>Feature Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE__FEATURE_CARDINALITY = 2;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE__ATTRIBUTES = 3;

	/**
	 * The number of structural features of the '<em>Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link moon.nju.edu.cn.fm.model.impl.OrFeatureImpl <em>Or Feature</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see moon.nju.edu.cn.fm.model.impl.OrFeatureImpl
	 * @see moon.nju.edu.cn.fm.model.impl.SFEAPackageImpl#getOrFeature()
	 * @generated
	 */
	int OR_FEATURE = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR_FEATURE__NAME = FEATURE__NAME;

	/**
	 * The feature id for the '<em><b>Sub Features</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR_FEATURE__SUB_FEATURES = FEATURE__SUB_FEATURES;

	/**
	 * The feature id for the '<em><b>Feature Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR_FEATURE__FEATURE_CARDINALITY = FEATURE__FEATURE_CARDINALITY;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR_FEATURE__ATTRIBUTES = FEATURE__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Variants</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR_FEATURE__VARIANTS = FEATURE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Group Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR_FEATURE__GROUP_CARDINALITY = FEATURE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Or Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR_FEATURE_FEATURE_COUNT = FEATURE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Or Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR_FEATURE_OPERATION_COUNT = FEATURE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link moon.nju.edu.cn.fm.model.impl.XorFeatureImpl <em>Xor Feature</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see moon.nju.edu.cn.fm.model.impl.XorFeatureImpl
	 * @see moon.nju.edu.cn.fm.model.impl.SFEAPackageImpl#getXorFeature()
	 * @generated
	 */
	int XOR_FEATURE = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XOR_FEATURE__NAME = OR_FEATURE__NAME;

	/**
	 * The feature id for the '<em><b>Sub Features</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XOR_FEATURE__SUB_FEATURES = OR_FEATURE__SUB_FEATURES;

	/**
	 * The feature id for the '<em><b>Feature Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XOR_FEATURE__FEATURE_CARDINALITY = OR_FEATURE__FEATURE_CARDINALITY;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XOR_FEATURE__ATTRIBUTES = OR_FEATURE__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Variants</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XOR_FEATURE__VARIANTS = OR_FEATURE__VARIANTS;

	/**
	 * The feature id for the '<em><b>Group Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XOR_FEATURE__GROUP_CARDINALITY = OR_FEATURE__GROUP_CARDINALITY;

	/**
	 * The number of structural features of the '<em>Xor Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XOR_FEATURE_FEATURE_COUNT = OR_FEATURE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Xor Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XOR_FEATURE_OPERATION_COUNT = OR_FEATURE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link moon.nju.edu.cn.fm.model.impl.CardinalityImpl <em>Cardinality</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see moon.nju.edu.cn.fm.model.impl.CardinalityImpl
	 * @see moon.nju.edu.cn.fm.model.impl.SFEAPackageImpl#getCardinality()
	 * @generated
	 */
	int CARDINALITY = 4;

	/**
	 * The feature id for the '<em><b>Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CARDINALITY__MIN = 0;

	/**
	 * The feature id for the '<em><b>Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CARDINALITY__MAX = 1;

	/**
	 * The number of structural features of the '<em>Cardinality</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CARDINALITY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Cardinality</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CARDINALITY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link moon.nju.edu.cn.fm.model.impl.FeatureCardinalityImpl <em>Feature Cardinality</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see moon.nju.edu.cn.fm.model.impl.FeatureCardinalityImpl
	 * @see moon.nju.edu.cn.fm.model.impl.SFEAPackageImpl#getFeatureCardinality()
	 * @generated
	 */
	int FEATURE_CARDINALITY = 5;

	/**
	 * The feature id for the '<em><b>Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_CARDINALITY__MIN = CARDINALITY__MIN;

	/**
	 * The feature id for the '<em><b>Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_CARDINALITY__MAX = CARDINALITY__MAX;

	/**
	 * The number of structural features of the '<em>Feature Cardinality</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_CARDINALITY_FEATURE_COUNT = CARDINALITY_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Feature Cardinality</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_CARDINALITY_OPERATION_COUNT = CARDINALITY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link moon.nju.edu.cn.fm.model.impl.GroupCardinalityImpl <em>Group Cardinality</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see moon.nju.edu.cn.fm.model.impl.GroupCardinalityImpl
	 * @see moon.nju.edu.cn.fm.model.impl.SFEAPackageImpl#getGroupCardinality()
	 * @generated
	 */
	int GROUP_CARDINALITY = 6;

	/**
	 * The feature id for the '<em><b>Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_CARDINALITY__MIN = CARDINALITY__MIN;

	/**
	 * The feature id for the '<em><b>Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_CARDINALITY__MAX = CARDINALITY__MAX;

	/**
	 * The number of structural features of the '<em>Group Cardinality</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_CARDINALITY_FEATURE_COUNT = CARDINALITY_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Group Cardinality</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_CARDINALITY_OPERATION_COUNT = CARDINALITY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link moon.nju.edu.cn.fm.model.impl.AttributeImpl <em>Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see moon.nju.edu.cn.fm.model.impl.AttributeImpl
	 * @see moon.nju.edu.cn.fm.model.impl.SFEAPackageImpl#getAttribute()
	 * @generated
	 */
	int ATTRIBUTE = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link moon.nju.edu.cn.fm.model.impl.ConstraintsImpl <em>Constraints</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see moon.nju.edu.cn.fm.model.impl.ConstraintsImpl
	 * @see moon.nju.edu.cn.fm.model.impl.SFEAPackageImpl#getConstraints()
	 * @generated
	 */
	int CONSTRAINTS = 8;

	/**
	 * The number of structural features of the '<em>Constraints</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINTS_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Constraints</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINTS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link moon.nju.edu.cn.fm.model.impl.CardExConstraintImpl <em>Card Ex Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see moon.nju.edu.cn.fm.model.impl.CardExConstraintImpl
	 * @see moon.nju.edu.cn.fm.model.impl.SFEAPackageImpl#getCardExConstraint()
	 * @generated
	 */
	int CARD_EX_CONSTRAINT = 9;

	/**
	 * The feature id for the '<em><b>Action</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CARD_EX_CONSTRAINT__ACTION = CONSTRAINTS_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CARD_EX_CONSTRAINT__CONDITION = CONSTRAINTS_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CARD_EX_CONSTRAINT__OPERATOR = CONSTRAINTS_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Card Ex Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CARD_EX_CONSTRAINT_FEATURE_COUNT = CONSTRAINTS_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Card Ex Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CARD_EX_CONSTRAINT_OPERATION_COUNT = CONSTRAINTS_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link moon.nju.edu.cn.fm.model.impl.OperatorImpl <em>Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see moon.nju.edu.cn.fm.model.impl.OperatorImpl
	 * @see moon.nju.edu.cn.fm.model.impl.SFEAPackageImpl#getOperator()
	 * @generated
	 */
	int OPERATOR = 10;

	/**
	 * The number of structural features of the '<em>Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link moon.nju.edu.cn.fm.model.impl.AndOperatorImpl <em>And Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see moon.nju.edu.cn.fm.model.impl.AndOperatorImpl
	 * @see moon.nju.edu.cn.fm.model.impl.SFEAPackageImpl#getAndOperator()
	 * @generated
	 */
	int AND_OPERATOR = 11;

	/**
	 * The number of structural features of the '<em>And Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AND_OPERATOR_FEATURE_COUNT = OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>And Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AND_OPERATOR_OPERATION_COUNT = OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link moon.nju.edu.cn.fm.model.impl.OrOperatorImpl <em>Or Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see moon.nju.edu.cn.fm.model.impl.OrOperatorImpl
	 * @see moon.nju.edu.cn.fm.model.impl.SFEAPackageImpl#getOrOperator()
	 * @generated
	 */
	int OR_OPERATOR = 12;

	/**
	 * The number of structural features of the '<em>Or Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR_OPERATOR_FEATURE_COUNT = OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Or Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR_OPERATOR_OPERATION_COUNT = OPERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link moon.nju.edu.cn.fm.model.impl.OperationImpl <em>Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see moon.nju.edu.cn.fm.model.impl.OperationImpl
	 * @see moon.nju.edu.cn.fm.model.impl.SFEAPackageImpl#getOperation()
	 * @generated
	 */
	int OPERATION = 13;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__FEATURE = 0;

	/**
	 * The number of structural features of the '<em>Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link moon.nju.edu.cn.fm.model.impl.ValueOperationImpl <em>Value Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see moon.nju.edu.cn.fm.model.impl.ValueOperationImpl
	 * @see moon.nju.edu.cn.fm.model.impl.SFEAPackageImpl#getValueOperation()
	 * @generated
	 */
	int VALUE_OPERATION = 14;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_OPERATION__FEATURE = OPERATION__FEATURE;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_OPERATION__VALUE = OPERATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Value Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_OPERATION_FEATURE_COUNT = OPERATION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Value Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_OPERATION_OPERATION_COUNT = OPERATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link moon.nju.edu.cn.fm.model.impl.BooleanConstraintsImpl <em>Boolean Constraints</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see moon.nju.edu.cn.fm.model.impl.BooleanConstraintsImpl
	 * @see moon.nju.edu.cn.fm.model.impl.SFEAPackageImpl#getBooleanConstraints()
	 * @generated
	 */
	int BOOLEAN_CONSTRAINTS = 15;

	/**
	 * The feature id for the '<em><b>From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_CONSTRAINTS__FROM = CONSTRAINTS_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_CONSTRAINTS__TO = CONSTRAINTS_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Boolean Constraints</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_CONSTRAINTS_FEATURE_COUNT = CONSTRAINTS_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Boolean Constraints</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_CONSTRAINTS_OPERATION_COUNT = CONSTRAINTS_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link moon.nju.edu.cn.fm.model.impl.ImpliesConstraintsImpl <em>Implies Constraints</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see moon.nju.edu.cn.fm.model.impl.ImpliesConstraintsImpl
	 * @see moon.nju.edu.cn.fm.model.impl.SFEAPackageImpl#getImpliesConstraints()
	 * @generated
	 */
	int IMPLIES_CONSTRAINTS = 16;

	/**
	 * The feature id for the '<em><b>From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLIES_CONSTRAINTS__FROM = BOOLEAN_CONSTRAINTS__FROM;

	/**
	 * The feature id for the '<em><b>To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLIES_CONSTRAINTS__TO = BOOLEAN_CONSTRAINTS__TO;

	/**
	 * The number of structural features of the '<em>Implies Constraints</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLIES_CONSTRAINTS_FEATURE_COUNT = BOOLEAN_CONSTRAINTS_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Implies Constraints</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLIES_CONSTRAINTS_OPERATION_COUNT = BOOLEAN_CONSTRAINTS_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link moon.nju.edu.cn.fm.model.impl.ExcludesConstraintsImpl <em>Excludes Constraints</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see moon.nju.edu.cn.fm.model.impl.ExcludesConstraintsImpl
	 * @see moon.nju.edu.cn.fm.model.impl.SFEAPackageImpl#getExcludesConstraints()
	 * @generated
	 */
	int EXCLUDES_CONSTRAINTS = 17;

	/**
	 * The feature id for the '<em><b>From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCLUDES_CONSTRAINTS__FROM = BOOLEAN_CONSTRAINTS__FROM;

	/**
	 * The feature id for the '<em><b>To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCLUDES_CONSTRAINTS__TO = BOOLEAN_CONSTRAINTS__TO;

	/**
	 * The number of structural features of the '<em>Excludes Constraints</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCLUDES_CONSTRAINTS_FEATURE_COUNT = BOOLEAN_CONSTRAINTS_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Excludes Constraints</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCLUDES_CONSTRAINTS_OPERATION_COUNT = BOOLEAN_CONSTRAINTS_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link moon.nju.edu.cn.fm.model.FeatureModel <em>Feature Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature Model</em>'.
	 * @see moon.nju.edu.cn.fm.model.FeatureModel
	 * @generated
	 */
	EClass getFeatureModel();

	/**
	 * Returns the meta object for the containment reference '{@link moon.nju.edu.cn.fm.model.FeatureModel#getRoot <em>Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Root</em>'.
	 * @see moon.nju.edu.cn.fm.model.FeatureModel#getRoot()
	 * @see #getFeatureModel()
	 * @generated
	 */
	EReference getFeatureModel_Root();

	/**
	 * Returns the meta object for the containment reference list '{@link moon.nju.edu.cn.fm.model.FeatureModel#getConstraints <em>Constraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Constraints</em>'.
	 * @see moon.nju.edu.cn.fm.model.FeatureModel#getConstraints()
	 * @see #getFeatureModel()
	 * @generated
	 */
	EReference getFeatureModel_Constraints();

	/**
	 * Returns the meta object for class '{@link moon.nju.edu.cn.fm.model.Feature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature</em>'.
	 * @see moon.nju.edu.cn.fm.model.Feature
	 * @generated
	 */
	EClass getFeature();

	/**
	 * Returns the meta object for the attribute '{@link moon.nju.edu.cn.fm.model.Feature#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see moon.nju.edu.cn.fm.model.Feature#getName()
	 * @see #getFeature()
	 * @generated
	 */
	EAttribute getFeature_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link moon.nju.edu.cn.fm.model.Feature#getSubFeatures <em>Sub Features</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sub Features</em>'.
	 * @see moon.nju.edu.cn.fm.model.Feature#getSubFeatures()
	 * @see #getFeature()
	 * @generated
	 */
	EReference getFeature_SubFeatures();

	/**
	 * Returns the meta object for the containment reference '{@link moon.nju.edu.cn.fm.model.Feature#getFeatureCardinality <em>Feature Cardinality</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Feature Cardinality</em>'.
	 * @see moon.nju.edu.cn.fm.model.Feature#getFeatureCardinality()
	 * @see #getFeature()
	 * @generated
	 */
	EReference getFeature_FeatureCardinality();

	/**
	 * Returns the meta object for the containment reference list '{@link moon.nju.edu.cn.fm.model.Feature#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attributes</em>'.
	 * @see moon.nju.edu.cn.fm.model.Feature#getAttributes()
	 * @see #getFeature()
	 * @generated
	 */
	EReference getFeature_Attributes();

	/**
	 * Returns the meta object for class '{@link moon.nju.edu.cn.fm.model.OrFeature <em>Or Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Or Feature</em>'.
	 * @see moon.nju.edu.cn.fm.model.OrFeature
	 * @generated
	 */
	EClass getOrFeature();

	/**
	 * Returns the meta object for the containment reference list '{@link moon.nju.edu.cn.fm.model.OrFeature#getVariants <em>Variants</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Variants</em>'.
	 * @see moon.nju.edu.cn.fm.model.OrFeature#getVariants()
	 * @see #getOrFeature()
	 * @generated
	 */
	EReference getOrFeature_Variants();

	/**
	 * Returns the meta object for the containment reference '{@link moon.nju.edu.cn.fm.model.OrFeature#getGroupCardinality <em>Group Cardinality</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Group Cardinality</em>'.
	 * @see moon.nju.edu.cn.fm.model.OrFeature#getGroupCardinality()
	 * @see #getOrFeature()
	 * @generated
	 */
	EReference getOrFeature_GroupCardinality();

	/**
	 * Returns the meta object for class '{@link moon.nju.edu.cn.fm.model.XorFeature <em>Xor Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Xor Feature</em>'.
	 * @see moon.nju.edu.cn.fm.model.XorFeature
	 * @generated
	 */
	EClass getXorFeature();

	/**
	 * Returns the meta object for class '{@link moon.nju.edu.cn.fm.model.Cardinality <em>Cardinality</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cardinality</em>'.
	 * @see moon.nju.edu.cn.fm.model.Cardinality
	 * @generated
	 */
	EClass getCardinality();

	/**
	 * Returns the meta object for the attribute '{@link moon.nju.edu.cn.fm.model.Cardinality#getMin <em>Min</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min</em>'.
	 * @see moon.nju.edu.cn.fm.model.Cardinality#getMin()
	 * @see #getCardinality()
	 * @generated
	 */
	EAttribute getCardinality_Min();

	/**
	 * Returns the meta object for the attribute '{@link moon.nju.edu.cn.fm.model.Cardinality#getMax <em>Max</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max</em>'.
	 * @see moon.nju.edu.cn.fm.model.Cardinality#getMax()
	 * @see #getCardinality()
	 * @generated
	 */
	EAttribute getCardinality_Max();

	/**
	 * Returns the meta object for class '{@link moon.nju.edu.cn.fm.model.FeatureCardinality <em>Feature Cardinality</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature Cardinality</em>'.
	 * @see moon.nju.edu.cn.fm.model.FeatureCardinality
	 * @generated
	 */
	EClass getFeatureCardinality();

	/**
	 * Returns the meta object for class '{@link moon.nju.edu.cn.fm.model.GroupCardinality <em>Group Cardinality</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Group Cardinality</em>'.
	 * @see moon.nju.edu.cn.fm.model.GroupCardinality
	 * @generated
	 */
	EClass getGroupCardinality();

	/**
	 * Returns the meta object for class '{@link moon.nju.edu.cn.fm.model.Attribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute</em>'.
	 * @see moon.nju.edu.cn.fm.model.Attribute
	 * @generated
	 */
	EClass getAttribute();

	/**
	 * Returns the meta object for the attribute '{@link moon.nju.edu.cn.fm.model.Attribute#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see moon.nju.edu.cn.fm.model.Attribute#getName()
	 * @see #getAttribute()
	 * @generated
	 */
	EAttribute getAttribute_Name();

	/**
	 * Returns the meta object for the attribute '{@link moon.nju.edu.cn.fm.model.Attribute#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see moon.nju.edu.cn.fm.model.Attribute#getValue()
	 * @see #getAttribute()
	 * @generated
	 */
	EAttribute getAttribute_Value();

	/**
	 * Returns the meta object for class '{@link moon.nju.edu.cn.fm.model.Constraints <em>Constraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constraints</em>'.
	 * @see moon.nju.edu.cn.fm.model.Constraints
	 * @generated
	 */
	EClass getConstraints();

	/**
	 * Returns the meta object for class '{@link moon.nju.edu.cn.fm.model.CardExConstraint <em>Card Ex Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Card Ex Constraint</em>'.
	 * @see moon.nju.edu.cn.fm.model.CardExConstraint
	 * @generated
	 */
	EClass getCardExConstraint();

	/**
	 * Returns the meta object for the containment reference '{@link moon.nju.edu.cn.fm.model.CardExConstraint#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Action</em>'.
	 * @see moon.nju.edu.cn.fm.model.CardExConstraint#getAction()
	 * @see #getCardExConstraint()
	 * @generated
	 */
	EReference getCardExConstraint_Action();

	/**
	 * Returns the meta object for the containment reference list '{@link moon.nju.edu.cn.fm.model.CardExConstraint#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Condition</em>'.
	 * @see moon.nju.edu.cn.fm.model.CardExConstraint#getCondition()
	 * @see #getCardExConstraint()
	 * @generated
	 */
	EReference getCardExConstraint_Condition();

	/**
	 * Returns the meta object for the containment reference '{@link moon.nju.edu.cn.fm.model.CardExConstraint#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Operator</em>'.
	 * @see moon.nju.edu.cn.fm.model.CardExConstraint#getOperator()
	 * @see #getCardExConstraint()
	 * @generated
	 */
	EReference getCardExConstraint_Operator();

	/**
	 * Returns the meta object for class '{@link moon.nju.edu.cn.fm.model.Operator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operator</em>'.
	 * @see moon.nju.edu.cn.fm.model.Operator
	 * @generated
	 */
	EClass getOperator();

	/**
	 * Returns the meta object for class '{@link moon.nju.edu.cn.fm.model.AndOperator <em>And Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>And Operator</em>'.
	 * @see moon.nju.edu.cn.fm.model.AndOperator
	 * @generated
	 */
	EClass getAndOperator();

	/**
	 * Returns the meta object for class '{@link moon.nju.edu.cn.fm.model.OrOperator <em>Or Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Or Operator</em>'.
	 * @see moon.nju.edu.cn.fm.model.OrOperator
	 * @generated
	 */
	EClass getOrOperator();

	/**
	 * Returns the meta object for class '{@link moon.nju.edu.cn.fm.model.Operation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation</em>'.
	 * @see moon.nju.edu.cn.fm.model.Operation
	 * @generated
	 */
	EClass getOperation();

	/**
	 * Returns the meta object for the reference '{@link moon.nju.edu.cn.fm.model.Operation#getFeature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Feature</em>'.
	 * @see moon.nju.edu.cn.fm.model.Operation#getFeature()
	 * @see #getOperation()
	 * @generated
	 */
	EReference getOperation_Feature();

	/**
	 * Returns the meta object for class '{@link moon.nju.edu.cn.fm.model.ValueOperation <em>Value Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value Operation</em>'.
	 * @see moon.nju.edu.cn.fm.model.ValueOperation
	 * @generated
	 */
	EClass getValueOperation();

	/**
	 * Returns the meta object for the attribute '{@link moon.nju.edu.cn.fm.model.ValueOperation#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see moon.nju.edu.cn.fm.model.ValueOperation#getValue()
	 * @see #getValueOperation()
	 * @generated
	 */
	EAttribute getValueOperation_Value();

	/**
	 * Returns the meta object for class '{@link moon.nju.edu.cn.fm.model.BooleanConstraints <em>Boolean Constraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Constraints</em>'.
	 * @see moon.nju.edu.cn.fm.model.BooleanConstraints
	 * @generated
	 */
	EClass getBooleanConstraints();

	/**
	 * Returns the meta object for the reference '{@link moon.nju.edu.cn.fm.model.BooleanConstraints#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>From</em>'.
	 * @see moon.nju.edu.cn.fm.model.BooleanConstraints#getFrom()
	 * @see #getBooleanConstraints()
	 * @generated
	 */
	EReference getBooleanConstraints_From();

	/**
	 * Returns the meta object for the reference '{@link moon.nju.edu.cn.fm.model.BooleanConstraints#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>To</em>'.
	 * @see moon.nju.edu.cn.fm.model.BooleanConstraints#getTo()
	 * @see #getBooleanConstraints()
	 * @generated
	 */
	EReference getBooleanConstraints_To();

	/**
	 * Returns the meta object for class '{@link moon.nju.edu.cn.fm.model.ImpliesConstraints <em>Implies Constraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Implies Constraints</em>'.
	 * @see moon.nju.edu.cn.fm.model.ImpliesConstraints
	 * @generated
	 */
	EClass getImpliesConstraints();

	/**
	 * Returns the meta object for class '{@link moon.nju.edu.cn.fm.model.ExcludesConstraints <em>Excludes Constraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Excludes Constraints</em>'.
	 * @see moon.nju.edu.cn.fm.model.ExcludesConstraints
	 * @generated
	 */
	EClass getExcludesConstraints();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SFEAFactory getSFEAFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link moon.nju.edu.cn.fm.model.impl.FeatureModelImpl <em>Feature Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see moon.nju.edu.cn.fm.model.impl.FeatureModelImpl
		 * @see moon.nju.edu.cn.fm.model.impl.SFEAPackageImpl#getFeatureModel()
		 * @generated
		 */
		EClass FEATURE_MODEL = eINSTANCE.getFeatureModel();

		/**
		 * The meta object literal for the '<em><b>Root</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FEATURE_MODEL__ROOT = eINSTANCE.getFeatureModel_Root();

		/**
		 * The meta object literal for the '<em><b>Constraints</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FEATURE_MODEL__CONSTRAINTS = eINSTANCE.getFeatureModel_Constraints();

		/**
		 * The meta object literal for the '{@link moon.nju.edu.cn.fm.model.impl.FeatureImpl <em>Feature</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see moon.nju.edu.cn.fm.model.impl.FeatureImpl
		 * @see moon.nju.edu.cn.fm.model.impl.SFEAPackageImpl#getFeature()
		 * @generated
		 */
		EClass FEATURE = eINSTANCE.getFeature();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FEATURE__NAME = eINSTANCE.getFeature_Name();

		/**
		 * The meta object literal for the '<em><b>Sub Features</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FEATURE__SUB_FEATURES = eINSTANCE.getFeature_SubFeatures();

		/**
		 * The meta object literal for the '<em><b>Feature Cardinality</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FEATURE__FEATURE_CARDINALITY = eINSTANCE.getFeature_FeatureCardinality();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FEATURE__ATTRIBUTES = eINSTANCE.getFeature_Attributes();

		/**
		 * The meta object literal for the '{@link moon.nju.edu.cn.fm.model.impl.OrFeatureImpl <em>Or Feature</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see moon.nju.edu.cn.fm.model.impl.OrFeatureImpl
		 * @see moon.nju.edu.cn.fm.model.impl.SFEAPackageImpl#getOrFeature()
		 * @generated
		 */
		EClass OR_FEATURE = eINSTANCE.getOrFeature();

		/**
		 * The meta object literal for the '<em><b>Variants</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OR_FEATURE__VARIANTS = eINSTANCE.getOrFeature_Variants();

		/**
		 * The meta object literal for the '<em><b>Group Cardinality</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OR_FEATURE__GROUP_CARDINALITY = eINSTANCE.getOrFeature_GroupCardinality();

		/**
		 * The meta object literal for the '{@link moon.nju.edu.cn.fm.model.impl.XorFeatureImpl <em>Xor Feature</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see moon.nju.edu.cn.fm.model.impl.XorFeatureImpl
		 * @see moon.nju.edu.cn.fm.model.impl.SFEAPackageImpl#getXorFeature()
		 * @generated
		 */
		EClass XOR_FEATURE = eINSTANCE.getXorFeature();

		/**
		 * The meta object literal for the '{@link moon.nju.edu.cn.fm.model.impl.CardinalityImpl <em>Cardinality</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see moon.nju.edu.cn.fm.model.impl.CardinalityImpl
		 * @see moon.nju.edu.cn.fm.model.impl.SFEAPackageImpl#getCardinality()
		 * @generated
		 */
		EClass CARDINALITY = eINSTANCE.getCardinality();

		/**
		 * The meta object literal for the '<em><b>Min</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CARDINALITY__MIN = eINSTANCE.getCardinality_Min();

		/**
		 * The meta object literal for the '<em><b>Max</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CARDINALITY__MAX = eINSTANCE.getCardinality_Max();

		/**
		 * The meta object literal for the '{@link moon.nju.edu.cn.fm.model.impl.FeatureCardinalityImpl <em>Feature Cardinality</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see moon.nju.edu.cn.fm.model.impl.FeatureCardinalityImpl
		 * @see moon.nju.edu.cn.fm.model.impl.SFEAPackageImpl#getFeatureCardinality()
		 * @generated
		 */
		EClass FEATURE_CARDINALITY = eINSTANCE.getFeatureCardinality();

		/**
		 * The meta object literal for the '{@link moon.nju.edu.cn.fm.model.impl.GroupCardinalityImpl <em>Group Cardinality</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see moon.nju.edu.cn.fm.model.impl.GroupCardinalityImpl
		 * @see moon.nju.edu.cn.fm.model.impl.SFEAPackageImpl#getGroupCardinality()
		 * @generated
		 */
		EClass GROUP_CARDINALITY = eINSTANCE.getGroupCardinality();

		/**
		 * The meta object literal for the '{@link moon.nju.edu.cn.fm.model.impl.AttributeImpl <em>Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see moon.nju.edu.cn.fm.model.impl.AttributeImpl
		 * @see moon.nju.edu.cn.fm.model.impl.SFEAPackageImpl#getAttribute()
		 * @generated
		 */
		EClass ATTRIBUTE = eINSTANCE.getAttribute();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE__NAME = eINSTANCE.getAttribute_Name();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE__VALUE = eINSTANCE.getAttribute_Value();

		/**
		 * The meta object literal for the '{@link moon.nju.edu.cn.fm.model.impl.ConstraintsImpl <em>Constraints</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see moon.nju.edu.cn.fm.model.impl.ConstraintsImpl
		 * @see moon.nju.edu.cn.fm.model.impl.SFEAPackageImpl#getConstraints()
		 * @generated
		 */
		EClass CONSTRAINTS = eINSTANCE.getConstraints();

		/**
		 * The meta object literal for the '{@link moon.nju.edu.cn.fm.model.impl.CardExConstraintImpl <em>Card Ex Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see moon.nju.edu.cn.fm.model.impl.CardExConstraintImpl
		 * @see moon.nju.edu.cn.fm.model.impl.SFEAPackageImpl#getCardExConstraint()
		 * @generated
		 */
		EClass CARD_EX_CONSTRAINT = eINSTANCE.getCardExConstraint();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CARD_EX_CONSTRAINT__ACTION = eINSTANCE.getCardExConstraint_Action();

		/**
		 * The meta object literal for the '<em><b>Condition</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CARD_EX_CONSTRAINT__CONDITION = eINSTANCE.getCardExConstraint_Condition();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CARD_EX_CONSTRAINT__OPERATOR = eINSTANCE.getCardExConstraint_Operator();

		/**
		 * The meta object literal for the '{@link moon.nju.edu.cn.fm.model.impl.OperatorImpl <em>Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see moon.nju.edu.cn.fm.model.impl.OperatorImpl
		 * @see moon.nju.edu.cn.fm.model.impl.SFEAPackageImpl#getOperator()
		 * @generated
		 */
		EClass OPERATOR = eINSTANCE.getOperator();

		/**
		 * The meta object literal for the '{@link moon.nju.edu.cn.fm.model.impl.AndOperatorImpl <em>And Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see moon.nju.edu.cn.fm.model.impl.AndOperatorImpl
		 * @see moon.nju.edu.cn.fm.model.impl.SFEAPackageImpl#getAndOperator()
		 * @generated
		 */
		EClass AND_OPERATOR = eINSTANCE.getAndOperator();

		/**
		 * The meta object literal for the '{@link moon.nju.edu.cn.fm.model.impl.OrOperatorImpl <em>Or Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see moon.nju.edu.cn.fm.model.impl.OrOperatorImpl
		 * @see moon.nju.edu.cn.fm.model.impl.SFEAPackageImpl#getOrOperator()
		 * @generated
		 */
		EClass OR_OPERATOR = eINSTANCE.getOrOperator();

		/**
		 * The meta object literal for the '{@link moon.nju.edu.cn.fm.model.impl.OperationImpl <em>Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see moon.nju.edu.cn.fm.model.impl.OperationImpl
		 * @see moon.nju.edu.cn.fm.model.impl.SFEAPackageImpl#getOperation()
		 * @generated
		 */
		EClass OPERATION = eINSTANCE.getOperation();

		/**
		 * The meta object literal for the '<em><b>Feature</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION__FEATURE = eINSTANCE.getOperation_Feature();

		/**
		 * The meta object literal for the '{@link moon.nju.edu.cn.fm.model.impl.ValueOperationImpl <em>Value Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see moon.nju.edu.cn.fm.model.impl.ValueOperationImpl
		 * @see moon.nju.edu.cn.fm.model.impl.SFEAPackageImpl#getValueOperation()
		 * @generated
		 */
		EClass VALUE_OPERATION = eINSTANCE.getValueOperation();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALUE_OPERATION__VALUE = eINSTANCE.getValueOperation_Value();

		/**
		 * The meta object literal for the '{@link moon.nju.edu.cn.fm.model.impl.BooleanConstraintsImpl <em>Boolean Constraints</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see moon.nju.edu.cn.fm.model.impl.BooleanConstraintsImpl
		 * @see moon.nju.edu.cn.fm.model.impl.SFEAPackageImpl#getBooleanConstraints()
		 * @generated
		 */
		EClass BOOLEAN_CONSTRAINTS = eINSTANCE.getBooleanConstraints();

		/**
		 * The meta object literal for the '<em><b>From</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BOOLEAN_CONSTRAINTS__FROM = eINSTANCE.getBooleanConstraints_From();

		/**
		 * The meta object literal for the '<em><b>To</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BOOLEAN_CONSTRAINTS__TO = eINSTANCE.getBooleanConstraints_To();

		/**
		 * The meta object literal for the '{@link moon.nju.edu.cn.fm.model.impl.ImpliesConstraintsImpl <em>Implies Constraints</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see moon.nju.edu.cn.fm.model.impl.ImpliesConstraintsImpl
		 * @see moon.nju.edu.cn.fm.model.impl.SFEAPackageImpl#getImpliesConstraints()
		 * @generated
		 */
		EClass IMPLIES_CONSTRAINTS = eINSTANCE.getImpliesConstraints();

		/**
		 * The meta object literal for the '{@link moon.nju.edu.cn.fm.model.impl.ExcludesConstraintsImpl <em>Excludes Constraints</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see moon.nju.edu.cn.fm.model.impl.ExcludesConstraintsImpl
		 * @see moon.nju.edu.cn.fm.model.impl.SFEAPackageImpl#getExcludesConstraints()
		 * @generated
		 */
		EClass EXCLUDES_CONSTRAINTS = eINSTANCE.getExcludesConstraints();

	}

} //SFEAPackage
