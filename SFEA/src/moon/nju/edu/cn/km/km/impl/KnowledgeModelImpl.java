/**
 */
package moon.nju.edu.cn.km.km.impl;

import java.util.Collection;

import moon.nju.edu.cn.km.km.Concept;
import moon.nju.edu.cn.km.km.KMPackage;
import moon.nju.edu.cn.km.km.KnowledgeModel;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Knowledge Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link moon.nju.edu.cn.km.km.impl.KnowledgeModelImpl#getConcepts <em>Concepts</em>}</li>
 * </ul>
 *
 * @generated
 */
public class KnowledgeModelImpl extends MinimalEObjectImpl.Container implements KnowledgeModel {
	/**
	 * The cached value of the '{@link #getConcepts() <em>Concepts</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConcepts()
	 * @generated
	 * @ordered
	 */
	protected EList<Concept> concepts;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected KnowledgeModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return KMPackage.Literals.KNOWLEDGE_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Concept> getConcepts() {
		if (concepts == null) {
			concepts = new EObjectContainmentEList<Concept>(Concept.class, this, KMPackage.KNOWLEDGE_MODEL__CONCEPTS);
		}
		return concepts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case KMPackage.KNOWLEDGE_MODEL__CONCEPTS:
				return ((InternalEList<?>)getConcepts()).basicRemove(otherEnd, msgs);
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
			case KMPackage.KNOWLEDGE_MODEL__CONCEPTS:
				return getConcepts();
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
			case KMPackage.KNOWLEDGE_MODEL__CONCEPTS:
				getConcepts().clear();
				getConcepts().addAll((Collection<? extends Concept>)newValue);
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
			case KMPackage.KNOWLEDGE_MODEL__CONCEPTS:
				getConcepts().clear();
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
			case KMPackage.KNOWLEDGE_MODEL__CONCEPTS:
				return concepts != null && !concepts.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //KnowledgeModelImpl
