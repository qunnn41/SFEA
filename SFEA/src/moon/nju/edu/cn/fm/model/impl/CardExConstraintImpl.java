/**
 */
package moon.nju.edu.cn.fm.model.impl;

import java.util.Collection;

import moon.nju.edu.cn.fm.model.CardExConstraint;
import moon.nju.edu.cn.fm.model.Operation;
import moon.nju.edu.cn.fm.model.Operator;
import moon.nju.edu.cn.fm.model.SFEAPackage;
import moon.nju.edu.cn.fm.model.ValueOperation;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Card Ex Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link moon.nju.edu.cn.fm.model.impl.CardExConstraintImpl#getAction <em>Action</em>}</li>
 *   <li>{@link moon.nju.edu.cn.fm.model.impl.CardExConstraintImpl#getCondition <em>Condition</em>}</li>
 *   <li>{@link moon.nju.edu.cn.fm.model.impl.CardExConstraintImpl#getOperator <em>Operator</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CardExConstraintImpl extends ConstraintsImpl implements CardExConstraint {
	/**
	 * The cached value of the '{@link #getAction() <em>Action</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAction()
	 * @generated
	 * @ordered
	 */
	protected Operation action;

	/**
	 * The cached value of the '{@link #getCondition() <em>Condition</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCondition()
	 * @generated
	 * @ordered
	 */
	protected EList<ValueOperation> condition;

	/**
	 * The cached value of the '{@link #getOperator() <em>Operator</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperator()
	 * @generated
	 * @ordered
	 */
	protected Operator operator;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CardExConstraintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SFEAPackage.Literals.CARD_EX_CONSTRAINT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Operation getAction() {
		return action;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAction(Operation newAction, NotificationChain msgs) {
		Operation oldAction = action;
		action = newAction;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SFEAPackage.CARD_EX_CONSTRAINT__ACTION, oldAction, newAction);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAction(Operation newAction) {
		if (newAction != action) {
			NotificationChain msgs = null;
			if (action != null)
				msgs = ((InternalEObject)action).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SFEAPackage.CARD_EX_CONSTRAINT__ACTION, null, msgs);
			if (newAction != null)
				msgs = ((InternalEObject)newAction).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SFEAPackage.CARD_EX_CONSTRAINT__ACTION, null, msgs);
			msgs = basicSetAction(newAction, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SFEAPackage.CARD_EX_CONSTRAINT__ACTION, newAction, newAction));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ValueOperation> getCondition() {
		if (condition == null) {
			condition = new EObjectContainmentEList<ValueOperation>(ValueOperation.class, this, SFEAPackage.CARD_EX_CONSTRAINT__CONDITION);
		}
		return condition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Operator getOperator() {
		return operator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOperator(Operator newOperator, NotificationChain msgs) {
		Operator oldOperator = operator;
		operator = newOperator;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SFEAPackage.CARD_EX_CONSTRAINT__OPERATOR, oldOperator, newOperator);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperator(Operator newOperator) {
		if (newOperator != operator) {
			NotificationChain msgs = null;
			if (operator != null)
				msgs = ((InternalEObject)operator).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SFEAPackage.CARD_EX_CONSTRAINT__OPERATOR, null, msgs);
			if (newOperator != null)
				msgs = ((InternalEObject)newOperator).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SFEAPackage.CARD_EX_CONSTRAINT__OPERATOR, null, msgs);
			msgs = basicSetOperator(newOperator, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SFEAPackage.CARD_EX_CONSTRAINT__OPERATOR, newOperator, newOperator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SFEAPackage.CARD_EX_CONSTRAINT__ACTION:
				return basicSetAction(null, msgs);
			case SFEAPackage.CARD_EX_CONSTRAINT__CONDITION:
				return ((InternalEList<?>)getCondition()).basicRemove(otherEnd, msgs);
			case SFEAPackage.CARD_EX_CONSTRAINT__OPERATOR:
				return basicSetOperator(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SFEAPackage.CARD_EX_CONSTRAINT__ACTION:
				return getAction();
			case SFEAPackage.CARD_EX_CONSTRAINT__CONDITION:
				return getCondition();
			case SFEAPackage.CARD_EX_CONSTRAINT__OPERATOR:
				return getOperator();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SFEAPackage.CARD_EX_CONSTRAINT__ACTION:
				setAction((Operation)newValue);
				return;
			case SFEAPackage.CARD_EX_CONSTRAINT__CONDITION:
				getCondition().clear();
				getCondition().addAll((Collection<? extends ValueOperation>)newValue);
				return;
			case SFEAPackage.CARD_EX_CONSTRAINT__OPERATOR:
				setOperator((Operator)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case SFEAPackage.CARD_EX_CONSTRAINT__ACTION:
				setAction((Operation)null);
				return;
			case SFEAPackage.CARD_EX_CONSTRAINT__CONDITION:
				getCondition().clear();
				return;
			case SFEAPackage.CARD_EX_CONSTRAINT__OPERATOR:
				setOperator((Operator)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case SFEAPackage.CARD_EX_CONSTRAINT__ACTION:
				return action != null;
			case SFEAPackage.CARD_EX_CONSTRAINT__CONDITION:
				return condition != null && !condition.isEmpty();
			case SFEAPackage.CARD_EX_CONSTRAINT__OPERATOR:
				return operator != null;
		}
		return super.eIsSet(featureID);
	}

} //CardExConstraintImpl
