/**
 */
package moon.nju.edu.cn.fm.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link moon.nju.edu.cn.fm.model.Operation#getFrom <em>From</em>}</li>
 *   <li>{@link moon.nju.edu.cn.fm.model.Operation#getTo <em>To</em>}</li>
 *   <li>{@link moon.nju.edu.cn.fm.model.Operation#getOp <em>Op</em>}</li>
 * </ul>
 *
 * @see moon.nju.edu.cn.fm.model.SFEAPackage#getOperation()
 * @model
 * @generated
 */
public interface Operation extends Constraints {
	/**
	 * Returns the value of the '<em><b>From</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From</em>' containment reference.
	 * @see #setFrom(Element)
	 * @see moon.nju.edu.cn.fm.model.SFEAPackage#getOperation_From()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Element getFrom();

	/**
	 * Sets the value of the '{@link moon.nju.edu.cn.fm.model.Operation#getFrom <em>From</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From</em>' containment reference.
	 * @see #getFrom()
	 * @generated
	 */
	void setFrom(Element value);

	/**
	 * Returns the value of the '<em><b>To</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To</em>' containment reference.
	 * @see #setTo(Element)
	 * @see moon.nju.edu.cn.fm.model.SFEAPackage#getOperation_To()
	 * @model containment="true"
	 * @generated
	 */
	Element getTo();

	/**
	 * Sets the value of the '{@link moon.nju.edu.cn.fm.model.Operation#getTo <em>To</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To</em>' containment reference.
	 * @see #getTo()
	 * @generated
	 */
	void setTo(Element value);

	/**
	 * Returns the value of the '<em><b>Op</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Op</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Op</em>' containment reference.
	 * @see #setOp(Operator)
	 * @see moon.nju.edu.cn.fm.model.SFEAPackage#getOperation_Op()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Operator getOp();

	/**
	 * Sets the value of the '{@link moon.nju.edu.cn.fm.model.Operation#getOp <em>Op</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Op</em>' containment reference.
	 * @see #getOp()
	 * @generated
	 */
	void setOp(Operator value);

} // Operation
