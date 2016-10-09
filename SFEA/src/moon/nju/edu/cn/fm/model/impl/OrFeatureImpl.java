/**
 */
package moon.nju.edu.cn.fm.model.impl;

import java.util.Collection;

import moon.nju.edu.cn.fm.model.Feature;
import moon.nju.edu.cn.fm.model.GroupCardinality;
import moon.nju.edu.cn.fm.model.OrFeature;
import moon.nju.edu.cn.fm.model.SFEAPackage;

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
 * An implementation of the model object '<em><b>Or Feature</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link moon.nju.edu.cn.fm.model.impl.OrFeatureImpl#getVariants <em>Variants</em>}</li>
 *   <li>{@link moon.nju.edu.cn.fm.model.impl.OrFeatureImpl#getGroupCardinality <em>Group Cardinality</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OrFeatureImpl extends FeatureImpl implements OrFeature {
	/**
	 * The cached value of the '{@link #getVariants() <em>Variants</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariants()
	 * @generated
	 * @ordered
	 */
	protected EList<Feature> variants;

	/**
	 * The cached value of the '{@link #getGroupCardinality() <em>Group Cardinality</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroupCardinality()
	 * @generated
	 * @ordered
	 */
	protected GroupCardinality groupCardinality;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OrFeatureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SFEAPackage.Literals.OR_FEATURE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Feature> getVariants() {
		if (variants == null) {
			variants = new EObjectContainmentEList<Feature>(Feature.class, this, SFEAPackage.OR_FEATURE__VARIANTS);
		}
		return variants;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GroupCardinality getGroupCardinality() {
		return groupCardinality;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGroupCardinality(GroupCardinality newGroupCardinality, NotificationChain msgs) {
		GroupCardinality oldGroupCardinality = groupCardinality;
		groupCardinality = newGroupCardinality;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SFEAPackage.OR_FEATURE__GROUP_CARDINALITY, oldGroupCardinality, newGroupCardinality);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGroupCardinality(GroupCardinality newGroupCardinality) {
		if (newGroupCardinality != groupCardinality) {
			NotificationChain msgs = null;
			if (groupCardinality != null)
				msgs = ((InternalEObject)groupCardinality).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SFEAPackage.OR_FEATURE__GROUP_CARDINALITY, null, msgs);
			if (newGroupCardinality != null)
				msgs = ((InternalEObject)newGroupCardinality).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SFEAPackage.OR_FEATURE__GROUP_CARDINALITY, null, msgs);
			msgs = basicSetGroupCardinality(newGroupCardinality, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SFEAPackage.OR_FEATURE__GROUP_CARDINALITY, newGroupCardinality, newGroupCardinality));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SFEAPackage.OR_FEATURE__VARIANTS:
				return ((InternalEList<?>)getVariants()).basicRemove(otherEnd, msgs);
			case SFEAPackage.OR_FEATURE__GROUP_CARDINALITY:
				return basicSetGroupCardinality(null, msgs);
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
			case SFEAPackage.OR_FEATURE__VARIANTS:
				return getVariants();
			case SFEAPackage.OR_FEATURE__GROUP_CARDINALITY:
				return getGroupCardinality();
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
			case SFEAPackage.OR_FEATURE__VARIANTS:
				getVariants().clear();
				getVariants().addAll((Collection<? extends Feature>)newValue);
				return;
			case SFEAPackage.OR_FEATURE__GROUP_CARDINALITY:
				setGroupCardinality((GroupCardinality)newValue);
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
			case SFEAPackage.OR_FEATURE__VARIANTS:
				getVariants().clear();
				return;
			case SFEAPackage.OR_FEATURE__GROUP_CARDINALITY:
				setGroupCardinality((GroupCardinality)null);
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
			case SFEAPackage.OR_FEATURE__VARIANTS:
				return variants != null && !variants.isEmpty();
			case SFEAPackage.OR_FEATURE__GROUP_CARDINALITY:
				return groupCardinality != null;
		}
		return super.eIsSet(featureID);
	}

} //OrFeatureImpl
