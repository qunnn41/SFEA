/**
 */
package moon.nju.edu.cn.fm.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link moon.nju.edu.cn.fm.model.Feature#getName <em>Name</em>}</li>
 *   <li>{@link moon.nju.edu.cn.fm.model.Feature#getSubFeatures <em>Sub Features</em>}</li>
 *   <li>{@link moon.nju.edu.cn.fm.model.Feature#getFeatureCardinality <em>Feature Cardinality</em>}</li>
 *   <li>{@link moon.nju.edu.cn.fm.model.Feature#getAttributes <em>Attributes</em>}</li>
 * </ul>
 *
 * @see moon.nju.edu.cn.fm.model.SFEAPackage#getFeature()
 * @model
 * @generated
 */
public interface Feature extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see moon.nju.edu.cn.fm.model.SFEAPackage#getFeature_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link moon.nju.edu.cn.fm.model.Feature#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Sub Features</b></em>' containment reference list.
	 * The list contents are of type {@link moon.nju.edu.cn.fm.model.Feature}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Features</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Features</em>' containment reference list.
	 * @see moon.nju.edu.cn.fm.model.SFEAPackage#getFeature_SubFeatures()
	 * @model containment="true"
	 * @generated
	 */
	EList<Feature> getSubFeatures();

	/**
	 * Returns the value of the '<em><b>Feature Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Feature Cardinality</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Feature Cardinality</em>' containment reference.
	 * @see #setFeatureCardinality(FeatureCardinality)
	 * @see moon.nju.edu.cn.fm.model.SFEAPackage#getFeature_FeatureCardinality()
	 * @model containment="true"
	 * @generated
	 */
	FeatureCardinality getFeatureCardinality();

	/**
	 * Sets the value of the '{@link moon.nju.edu.cn.fm.model.Feature#getFeatureCardinality <em>Feature Cardinality</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Feature Cardinality</em>' containment reference.
	 * @see #getFeatureCardinality()
	 * @generated
	 */
	void setFeatureCardinality(FeatureCardinality value);

	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link moon.nju.edu.cn.fm.model.Attribute}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' containment reference list.
	 * @see moon.nju.edu.cn.fm.model.SFEAPackage#getFeature_Attributes()
	 * @model containment="true"
	 * @generated
	 */
	EList<Attribute> getAttributes();

} // Feature
