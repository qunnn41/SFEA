/**
 */
package moon.nju.edu.cn.km.km;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Concept</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link moon.nju.edu.cn.km.km.Concept#getSubConcepts <em>Sub Concepts</em>}</li>
 *   <li>{@link moon.nju.edu.cn.km.km.Concept#getHasConcepts <em>Has Concepts</em>}</li>
 *   <li>{@link moon.nju.edu.cn.km.km.Concept#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see moon.nju.edu.cn.km.km.KMPackage#getConcept()
 * @model
 * @generated
 */
public interface Concept extends EObject {
	/**
	 * Returns the value of the '<em><b>Sub Concepts</b></em>' containment reference list.
	 * The list contents are of type {@link moon.nju.edu.cn.km.km.Concept}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Concepts</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Concepts</em>' containment reference list.
	 * @see moon.nju.edu.cn.km.km.KMPackage#getConcept_SubConcepts()
	 * @model containment="true"
	 * @generated
	 */
	EList<Concept> getSubConcepts();

	/**
	 * Returns the value of the '<em><b>Has Concepts</b></em>' reference list.
	 * The list contents are of type {@link moon.nju.edu.cn.km.km.Concept}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Concepts</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Concepts</em>' reference list.
	 * @see moon.nju.edu.cn.km.km.KMPackage#getConcept_HasConcepts()
	 * @model
	 * @generated
	 */
	EList<Concept> getHasConcepts();

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
	 * @see moon.nju.edu.cn.km.km.KMPackage#getConcept_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link moon.nju.edu.cn.km.km.Concept#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // Concept
