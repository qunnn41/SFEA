/**
 */
package moon.nju.edu.cn.km.km.impl;

import java.util.Collection;

import moon.nju.edu.cn.km.km.Concept;
import moon.nju.edu.cn.km.km.KMPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Concept</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link moon.nju.edu.cn.km.km.impl.ConceptImpl#getSubConcepts <em>Sub Concepts</em>}</li>
 *   <li>{@link moon.nju.edu.cn.km.km.impl.ConceptImpl#getHasConcepts <em>Has Concepts</em>}</li>
 *   <li>{@link moon.nju.edu.cn.km.km.impl.ConceptImpl#getName <em>Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConceptImpl extends MinimalEObjectImpl.Container implements Concept {
	/**
	 * The cached value of the '{@link #getSubConcepts() <em>Sub Concepts</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubConcepts()
	 * @generated
	 * @ordered
	 */
	protected EList<Concept> subConcepts;

	/**
	 * The cached value of the '{@link #getHasConcepts() <em>Has Concepts</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHasConcepts()
	 * @generated
	 * @ordered
	 */
	protected EList<Concept> hasConcepts;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConceptImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return KMPackage.Literals.CONCEPT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Concept> getSubConcepts() {
		if (subConcepts == null) {
			subConcepts = new EObjectContainmentEList<Concept>(Concept.class, this, KMPackage.CONCEPT__SUB_CONCEPTS);
		}
		return subConcepts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Concept> getHasConcepts() {
		if (hasConcepts == null) {
			hasConcepts = new EObjectResolvingEList<Concept>(Concept.class, this, KMPackage.CONCEPT__HAS_CONCEPTS);
		}
		return hasConcepts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KMPackage.CONCEPT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case KMPackage.CONCEPT__SUB_CONCEPTS:
				return ((InternalEList<?>)getSubConcepts()).basicRemove(otherEnd, msgs);
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
			case KMPackage.CONCEPT__SUB_CONCEPTS:
				return getSubConcepts();
			case KMPackage.CONCEPT__HAS_CONCEPTS:
				return getHasConcepts();
			case KMPackage.CONCEPT__NAME:
				return getName();
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
			case KMPackage.CONCEPT__SUB_CONCEPTS:
				getSubConcepts().clear();
				getSubConcepts().addAll((Collection<? extends Concept>)newValue);
				return;
			case KMPackage.CONCEPT__HAS_CONCEPTS:
				getHasConcepts().clear();
				getHasConcepts().addAll((Collection<? extends Concept>)newValue);
				return;
			case KMPackage.CONCEPT__NAME:
				setName((String)newValue);
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
			case KMPackage.CONCEPT__SUB_CONCEPTS:
				getSubConcepts().clear();
				return;
			case KMPackage.CONCEPT__HAS_CONCEPTS:
				getHasConcepts().clear();
				return;
			case KMPackage.CONCEPT__NAME:
				setName(NAME_EDEFAULT);
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
			case KMPackage.CONCEPT__SUB_CONCEPTS:
				return subConcepts != null && !subConcepts.isEmpty();
			case KMPackage.CONCEPT__HAS_CONCEPTS:
				return hasConcepts != null && !hasConcepts.isEmpty();
			case KMPackage.CONCEPT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //ConceptImpl
