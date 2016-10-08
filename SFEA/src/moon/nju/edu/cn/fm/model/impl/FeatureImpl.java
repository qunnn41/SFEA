/**
 */
package moon.nju.edu.cn.fm.model.impl;

import java.util.Collection;

import moon.nju.edu.cn.fm.model.Attribute;
import moon.nju.edu.cn.fm.model.Feature;
import moon.nju.edu.cn.fm.model.FeatureCardinality;
import moon.nju.edu.cn.fm.model.SFEAPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link moon.nju.edu.cn.fm.model.impl.FeatureImpl#getName <em>Name</em>}</li>
 *   <li>{@link moon.nju.edu.cn.fm.model.impl.FeatureImpl#getSubFeatures <em>Sub Features</em>}</li>
 *   <li>{@link moon.nju.edu.cn.fm.model.impl.FeatureImpl#getFeatureCardinality <em>Feature Cardinality</em>}</li>
 *   <li>{@link moon.nju.edu.cn.fm.model.impl.FeatureImpl#getAttributes <em>Attributes</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FeatureImpl extends MinimalEObjectImpl.Container implements Feature {
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
	 * The cached value of the '{@link #getSubFeatures() <em>Sub Features</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubFeatures()
	 * @generated
	 * @ordered
	 */
	protected EList subFeatures;

	/**
	 * The cached value of the '{@link #getFeatureCardinality() <em>Feature Cardinality</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeatureCardinality()
	 * @generated
	 * @ordered
	 */
	protected FeatureCardinality featureCardinality;

	/**
	 * The cached value of the '{@link #getAttributes() <em>Attributes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributes()
	 * @generated
	 * @ordered
	 */
	protected EList attributes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FeatureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SFEAPackage.Literals.FEATURE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, SFEAPackage.FEATURE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getSubFeatures() {
		if (subFeatures == null) {
			subFeatures = new EObjectContainmentEList(Feature.class, this, SFEAPackage.FEATURE__SUB_FEATURES);
		}
		return subFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureCardinality getFeatureCardinality() {
		return featureCardinality;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFeatureCardinality(FeatureCardinality newFeatureCardinality, NotificationChain msgs) {
		FeatureCardinality oldFeatureCardinality = featureCardinality;
		featureCardinality = newFeatureCardinality;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SFEAPackage.FEATURE__FEATURE_CARDINALITY, oldFeatureCardinality, newFeatureCardinality);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFeatureCardinality(FeatureCardinality newFeatureCardinality) {
		if (newFeatureCardinality != featureCardinality) {
			NotificationChain msgs = null;
			if (featureCardinality != null)
				msgs = ((InternalEObject)featureCardinality).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SFEAPackage.FEATURE__FEATURE_CARDINALITY, null, msgs);
			if (newFeatureCardinality != null)
				msgs = ((InternalEObject)newFeatureCardinality).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SFEAPackage.FEATURE__FEATURE_CARDINALITY, null, msgs);
			msgs = basicSetFeatureCardinality(newFeatureCardinality, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SFEAPackage.FEATURE__FEATURE_CARDINALITY, newFeatureCardinality, newFeatureCardinality));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getAttributes() {
		if (attributes == null) {
			attributes = new EObjectContainmentEList(Attribute.class, this, SFEAPackage.FEATURE__ATTRIBUTES);
		}
		return attributes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SFEAPackage.FEATURE__SUB_FEATURES:
				return ((InternalEList)getSubFeatures()).basicRemove(otherEnd, msgs);
			case SFEAPackage.FEATURE__FEATURE_CARDINALITY:
				return basicSetFeatureCardinality(null, msgs);
			case SFEAPackage.FEATURE__ATTRIBUTES:
				return ((InternalEList)getAttributes()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SFEAPackage.FEATURE__NAME:
				return getName();
			case SFEAPackage.FEATURE__SUB_FEATURES:
				return getSubFeatures();
			case SFEAPackage.FEATURE__FEATURE_CARDINALITY:
				return getFeatureCardinality();
			case SFEAPackage.FEATURE__ATTRIBUTES:
				return getAttributes();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SFEAPackage.FEATURE__NAME:
				setName((String)newValue);
				return;
			case SFEAPackage.FEATURE__SUB_FEATURES:
				getSubFeatures().clear();
				getSubFeatures().addAll((Collection)newValue);
				return;
			case SFEAPackage.FEATURE__FEATURE_CARDINALITY:
				setFeatureCardinality((FeatureCardinality)newValue);
				return;
			case SFEAPackage.FEATURE__ATTRIBUTES:
				getAttributes().clear();
				getAttributes().addAll((Collection)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case SFEAPackage.FEATURE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SFEAPackage.FEATURE__SUB_FEATURES:
				getSubFeatures().clear();
				return;
			case SFEAPackage.FEATURE__FEATURE_CARDINALITY:
				setFeatureCardinality((FeatureCardinality)null);
				return;
			case SFEAPackage.FEATURE__ATTRIBUTES:
				getAttributes().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case SFEAPackage.FEATURE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SFEAPackage.FEATURE__SUB_FEATURES:
				return subFeatures != null && !subFeatures.isEmpty();
			case SFEAPackage.FEATURE__FEATURE_CARDINALITY:
				return featureCardinality != null;
			case SFEAPackage.FEATURE__ATTRIBUTES:
				return attributes != null && !attributes.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //FeatureImpl
