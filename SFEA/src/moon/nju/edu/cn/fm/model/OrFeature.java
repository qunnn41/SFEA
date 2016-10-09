/**
 */
package moon.nju.edu.cn.fm.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Or Feature</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link moon.nju.edu.cn.fm.model.OrFeature#getVariants <em>Variants</em>}</li>
 *   <li>{@link moon.nju.edu.cn.fm.model.OrFeature#getGroupCardinality <em>Group Cardinality</em>}</li>
 * </ul>
 *
 * @see moon.nju.edu.cn.fm.model.SFEAPackage#getOrFeature()
 * @model
 * @generated
 */
public interface OrFeature extends Feature {
	/**
	 * Returns the value of the '<em><b>Variants</b></em>' containment reference list.
	 * The list contents are of type {@link moon.nju.edu.cn.fm.model.Feature}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variants</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variants</em>' containment reference list.
	 * @see moon.nju.edu.cn.fm.model.SFEAPackage#getOrFeature_Variants()
	 * @model containment="true" lower="2"
	 * @generated
	 */
	EList<Feature> getVariants();

	/**
	 * Returns the value of the '<em><b>Group Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group Cardinality</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group Cardinality</em>' containment reference.
	 * @see #setGroupCardinality(GroupCardinality)
	 * @see moon.nju.edu.cn.fm.model.SFEAPackage#getOrFeature_GroupCardinality()
	 * @model containment="true"
	 * @generated
	 */
	GroupCardinality getGroupCardinality();

	/**
	 * Sets the value of the '{@link moon.nju.edu.cn.fm.model.OrFeature#getGroupCardinality <em>Group Cardinality</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Group Cardinality</em>' containment reference.
	 * @see #getGroupCardinality()
	 * @generated
	 */
	void setGroupCardinality(GroupCardinality value);

} // OrFeature
