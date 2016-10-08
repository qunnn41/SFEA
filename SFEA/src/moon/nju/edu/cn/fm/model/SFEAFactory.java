/**
 */
package moon.nju.edu.cn.fm.model;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see moon.nju.edu.cn.fm.model.SFEAPackage
 * @generated
 */
public interface SFEAFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SFEAFactory eINSTANCE = moon.nju.edu.cn.fm.model.impl.SFEAFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Feature Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Feature Model</em>'.
	 * @generated
	 */
	FeatureModel createFeatureModel();

	/**
	 * Returns a new object of class '<em>Feature</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Feature</em>'.
	 * @generated
	 */
	Feature createFeature();

	/**
	 * Returns a new object of class '<em>Or Feature</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Or Feature</em>'.
	 * @generated
	 */
	OrFeature createOrFeature();

	/**
	 * Returns a new object of class '<em>Xor Feature</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Xor Feature</em>'.
	 * @generated
	 */
	XorFeature createXorFeature();

	/**
	 * Returns a new object of class '<em>Cardinality</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Cardinality</em>'.
	 * @generated
	 */
	Cardinality createCardinality();

	/**
	 * Returns a new object of class '<em>Feature Cardinality</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Feature Cardinality</em>'.
	 * @generated
	 */
	FeatureCardinality createFeatureCardinality();

	/**
	 * Returns a new object of class '<em>Group Cardinality</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Group Cardinality</em>'.
	 * @generated
	 */
	GroupCardinality createGroupCardinality();

	/**
	 * Returns a new object of class '<em>Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Attribute</em>'.
	 * @generated
	 */
	Attribute createAttribute();

	/**
	 * Returns a new object of class '<em>Constraints</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Constraints</em>'.
	 * @generated
	 */
	Constraints createConstraints();

	/**
	 * Returns a new object of class '<em>Boolean Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Boolean Constraint</em>'.
	 * @generated
	 */
	BooleanConstraint createBooleanConstraint();

	/**
	 * Returns a new object of class '<em>Implies Constraints</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Implies Constraints</em>'.
	 * @generated
	 */
	ImpliesConstraints createImpliesConstraints();

	/**
	 * Returns a new object of class '<em>Excludes</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Excludes</em>'.
	 * @generated
	 */
	Excludes createExcludes();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	SFEAPackage getSFEAPackage();

} //SFEAFactory
