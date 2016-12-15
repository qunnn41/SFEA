/**
 */
package moon.nju.edu.cn.km.km;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see moon.nju.edu.cn.km.km.KMPackage
 * @generated
 */
public interface KMFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	KMFactory eINSTANCE = moon.nju.edu.cn.km.km.impl.KMFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Knowledge Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Knowledge Model</em>'.
	 * @generated
	 */
	KnowledgeModel createKnowledgeModel();

	/**
	 * Returns a new object of class '<em>Concept</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Concept</em>'.
	 * @generated
	 */
	Concept createConcept();

	/**
	 * Returns a new object of class '<em>Countable Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Countable Element</em>'.
	 * @generated
	 */
	CountableElement createCountableElement();

	/**
	 * Returns a new object of class '<em>Quantifiable Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Quantifiable Element</em>'.
	 * @generated
	 */
	QuantifiableElement createQuantifiableElement();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	KMPackage getKMPackage();

} //KMFactory
