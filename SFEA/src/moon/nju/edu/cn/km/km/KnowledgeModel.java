/**
 */
package moon.nju.edu.cn.km.km;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Knowledge Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link moon.nju.edu.cn.km.km.KnowledgeModel#getConcepts <em>Concepts</em>}</li>
 * </ul>
 *
 * @see moon.nju.edu.cn.km.km.KMPackage#getKnowledgeModel()
 * @model
 * @generated
 */
public interface KnowledgeModel extends EObject {
	/**
	 * Returns the value of the '<em><b>Concepts</b></em>' containment reference list.
	 * The list contents are of type {@link moon.nju.edu.cn.km.km.Concept}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Concepts</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Concepts</em>' containment reference list.
	 * @see moon.nju.edu.cn.km.km.KMPackage#getKnowledgeModel_Concepts()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Concept> getConcepts();

} // KnowledgeModel
