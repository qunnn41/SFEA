/**
 */
package moon.nju.edu.cn.km.km;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see moon.nju.edu.cn.km.km.KMFactory
 * @model kind="package"
 * @generated
 */
public interface KMPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "km";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "www.example.com/km";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "km";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	KMPackage eINSTANCE = moon.nju.edu.cn.km.km.impl.KMPackageImpl.init();

	/**
	 * The meta object id for the '{@link moon.nju.edu.cn.km.km.impl.KnowledgeModelImpl <em>Knowledge Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see moon.nju.edu.cn.km.km.impl.KnowledgeModelImpl
	 * @see moon.nju.edu.cn.km.km.impl.KMPackageImpl#getKnowledgeModel()
	 * @generated
	 */
	int KNOWLEDGE_MODEL = 0;

	/**
	 * The feature id for the '<em><b>Concepts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KNOWLEDGE_MODEL__CONCEPTS = 0;

	/**
	 * The number of structural features of the '<em>Knowledge Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KNOWLEDGE_MODEL_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Knowledge Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KNOWLEDGE_MODEL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link moon.nju.edu.cn.km.km.impl.ConceptImpl <em>Concept</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see moon.nju.edu.cn.km.km.impl.ConceptImpl
	 * @see moon.nju.edu.cn.km.km.impl.KMPackageImpl#getConcept()
	 * @generated
	 */
	int CONCEPT = 1;

	/**
	 * The feature id for the '<em><b>Sub Concepts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__SUB_CONCEPTS = 0;

	/**
	 * The feature id for the '<em><b>Has Concepts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__HAS_CONCEPTS = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__NAME = 2;

	/**
	 * The number of structural features of the '<em>Concept</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Concept</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link moon.nju.edu.cn.km.km.impl.CountableElementImpl <em>Countable Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see moon.nju.edu.cn.km.km.impl.CountableElementImpl
	 * @see moon.nju.edu.cn.km.km.impl.KMPackageImpl#getCountableElement()
	 * @generated
	 */
	int COUNTABLE_ELEMENT = 2;

	/**
	 * The feature id for the '<em><b>Sub Concepts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COUNTABLE_ELEMENT__SUB_CONCEPTS = CONCEPT__SUB_CONCEPTS;

	/**
	 * The feature id for the '<em><b>Has Concepts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COUNTABLE_ELEMENT__HAS_CONCEPTS = CONCEPT__HAS_CONCEPTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COUNTABLE_ELEMENT__NAME = CONCEPT__NAME;

	/**
	 * The number of structural features of the '<em>Countable Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COUNTABLE_ELEMENT_FEATURE_COUNT = CONCEPT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Countable Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COUNTABLE_ELEMENT_OPERATION_COUNT = CONCEPT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link moon.nju.edu.cn.km.km.impl.QuantifiableElementImpl <em>Quantifiable Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see moon.nju.edu.cn.km.km.impl.QuantifiableElementImpl
	 * @see moon.nju.edu.cn.km.km.impl.KMPackageImpl#getQuantifiableElement()
	 * @generated
	 */
	int QUANTIFIABLE_ELEMENT = 3;

	/**
	 * The feature id for the '<em><b>Sub Concepts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUANTIFIABLE_ELEMENT__SUB_CONCEPTS = CONCEPT__SUB_CONCEPTS;

	/**
	 * The feature id for the '<em><b>Has Concepts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUANTIFIABLE_ELEMENT__HAS_CONCEPTS = CONCEPT__HAS_CONCEPTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUANTIFIABLE_ELEMENT__NAME = CONCEPT__NAME;

	/**
	 * The number of structural features of the '<em>Quantifiable Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUANTIFIABLE_ELEMENT_FEATURE_COUNT = CONCEPT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Quantifiable Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUANTIFIABLE_ELEMENT_OPERATION_COUNT = CONCEPT_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link moon.nju.edu.cn.km.km.KnowledgeModel <em>Knowledge Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Knowledge Model</em>'.
	 * @see moon.nju.edu.cn.km.km.KnowledgeModel
	 * @generated
	 */
	EClass getKnowledgeModel();

	/**
	 * Returns the meta object for the containment reference list '{@link moon.nju.edu.cn.km.km.KnowledgeModel#getConcepts <em>Concepts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Concepts</em>'.
	 * @see moon.nju.edu.cn.km.km.KnowledgeModel#getConcepts()
	 * @see #getKnowledgeModel()
	 * @generated
	 */
	EReference getKnowledgeModel_Concepts();

	/**
	 * Returns the meta object for class '{@link moon.nju.edu.cn.km.km.Concept <em>Concept</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Concept</em>'.
	 * @see moon.nju.edu.cn.km.km.Concept
	 * @generated
	 */
	EClass getConcept();

	/**
	 * Returns the meta object for the containment reference list '{@link moon.nju.edu.cn.km.km.Concept#getSubConcepts <em>Sub Concepts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sub Concepts</em>'.
	 * @see moon.nju.edu.cn.km.km.Concept#getSubConcepts()
	 * @see #getConcept()
	 * @generated
	 */
	EReference getConcept_SubConcepts();

	/**
	 * Returns the meta object for the reference list '{@link moon.nju.edu.cn.km.km.Concept#getHasConcepts <em>Has Concepts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Has Concepts</em>'.
	 * @see moon.nju.edu.cn.km.km.Concept#getHasConcepts()
	 * @see #getConcept()
	 * @generated
	 */
	EReference getConcept_HasConcepts();

	/**
	 * Returns the meta object for the attribute '{@link moon.nju.edu.cn.km.km.Concept#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see moon.nju.edu.cn.km.km.Concept#getName()
	 * @see #getConcept()
	 * @generated
	 */
	EAttribute getConcept_Name();

	/**
	 * Returns the meta object for class '{@link moon.nju.edu.cn.km.km.CountableElement <em>Countable Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Countable Element</em>'.
	 * @see moon.nju.edu.cn.km.km.CountableElement
	 * @generated
	 */
	EClass getCountableElement();

	/**
	 * Returns the meta object for class '{@link moon.nju.edu.cn.km.km.QuantifiableElement <em>Quantifiable Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Quantifiable Element</em>'.
	 * @see moon.nju.edu.cn.km.km.QuantifiableElement
	 * @generated
	 */
	EClass getQuantifiableElement();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	KMFactory getKMFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link moon.nju.edu.cn.km.km.impl.KnowledgeModelImpl <em>Knowledge Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see moon.nju.edu.cn.km.km.impl.KnowledgeModelImpl
		 * @see moon.nju.edu.cn.km.km.impl.KMPackageImpl#getKnowledgeModel()
		 * @generated
		 */
		EClass KNOWLEDGE_MODEL = eINSTANCE.getKnowledgeModel();

		/**
		 * The meta object literal for the '<em><b>Concepts</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference KNOWLEDGE_MODEL__CONCEPTS = eINSTANCE.getKnowledgeModel_Concepts();

		/**
		 * The meta object literal for the '{@link moon.nju.edu.cn.km.km.impl.ConceptImpl <em>Concept</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see moon.nju.edu.cn.km.km.impl.ConceptImpl
		 * @see moon.nju.edu.cn.km.km.impl.KMPackageImpl#getConcept()
		 * @generated
		 */
		EClass CONCEPT = eINSTANCE.getConcept();

		/**
		 * The meta object literal for the '<em><b>Sub Concepts</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONCEPT__SUB_CONCEPTS = eINSTANCE.getConcept_SubConcepts();

		/**
		 * The meta object literal for the '<em><b>Has Concepts</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONCEPT__HAS_CONCEPTS = eINSTANCE.getConcept_HasConcepts();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONCEPT__NAME = eINSTANCE.getConcept_Name();

		/**
		 * The meta object literal for the '{@link moon.nju.edu.cn.km.km.impl.CountableElementImpl <em>Countable Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see moon.nju.edu.cn.km.km.impl.CountableElementImpl
		 * @see moon.nju.edu.cn.km.km.impl.KMPackageImpl#getCountableElement()
		 * @generated
		 */
		EClass COUNTABLE_ELEMENT = eINSTANCE.getCountableElement();

		/**
		 * The meta object literal for the '{@link moon.nju.edu.cn.km.km.impl.QuantifiableElementImpl <em>Quantifiable Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see moon.nju.edu.cn.km.km.impl.QuantifiableElementImpl
		 * @see moon.nju.edu.cn.km.km.impl.KMPackageImpl#getQuantifiableElement()
		 * @generated
		 */
		EClass QUANTIFIABLE_ELEMENT = eINSTANCE.getQuantifiableElement();

	}

} //KMPackage
