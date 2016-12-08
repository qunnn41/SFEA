/**
 */
package moon.nju.edu.cn.fm.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Card Ex Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link moon.nju.edu.cn.fm.model.CardExConstraint#getAction <em>Action</em>}</li>
 *   <li>{@link moon.nju.edu.cn.fm.model.CardExConstraint#getCondition <em>Condition</em>}</li>
 *   <li>{@link moon.nju.edu.cn.fm.model.CardExConstraint#getOperator <em>Operator</em>}</li>
 * </ul>
 *
 * @see moon.nju.edu.cn.fm.model.SFEAPackage#getCardExConstraint()
 * @model
 * @generated
 */
public interface CardExConstraint extends Constraints {
	/**
	 * Returns the value of the '<em><b>Action</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Action</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Action</em>' containment reference.
	 * @see #setAction(Operation)
	 * @see moon.nju.edu.cn.fm.model.SFEAPackage#getCardExConstraint_Action()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Operation getAction();

	/**
	 * Sets the value of the '{@link moon.nju.edu.cn.fm.model.CardExConstraint#getAction <em>Action</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action</em>' containment reference.
	 * @see #getAction()
	 * @generated
	 */
	void setAction(Operation value);

	/**
	 * Returns the value of the '<em><b>Condition</b></em>' containment reference list.
	 * The list contents are of type {@link moon.nju.edu.cn.fm.model.Operation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition</em>' containment reference list.
	 * @see moon.nju.edu.cn.fm.model.SFEAPackage#getCardExConstraint_Condition()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Operation> getCondition();

	/**
	 * Returns the value of the '<em><b>Operator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operator</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' containment reference.
	 * @see #setOperator(Operator)
	 * @see moon.nju.edu.cn.fm.model.SFEAPackage#getCardExConstraint_Operator()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Operator getOperator();

	/**
	 * Sets the value of the '{@link moon.nju.edu.cn.fm.model.CardExConstraint#getOperator <em>Operator</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' containment reference.
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(Operator value);

} // CardExConstraint
