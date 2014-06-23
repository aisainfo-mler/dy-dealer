package com.ailk.yd.mapp.tibco;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * tibco个字典数据。变量名称遵循原则：TIBCO_变量类型_变量名称
 */
public class TibcoConstant {

	/**
	 * 号码预占接口返回response的状态
	 */
	public static String SELECT_SPEC_NUM_STATUS_SUCC = "0";
	/**
	 * 号码预占接口返回response的状态
	 */
	public static String SELECT_SPEC_NUM_STATUS_WARNING = "1";
	/**
	 * 号码预占接口返回response的状态
	 */
	public static String SELECT_SPEC_NUM_STATUS_ERR = "2";

	public static String TIBCO_Gender_Male = "2";
	public static String TIBCO_Gender_Female = "1";
	public static String TIBCO_Gender_Transgender = "0";
	public static String TIBCO_ConnectionType_LTEResidential = "Z01";
	public static String TIBCO_ConnectionType_LTEMobility = "Z02";
	public static String TIBCO_ConnectionType_Fiber = "Z03";
	public static String TIBCO_ConnectionType_WifiHotspot = "Z04";
	public static String TIBCO_ConnectionType_AirlineWifi = "Z05";
	public static String TIBCO_ConnectionType_LTEECS = "Z06";
	public static String TIBCO_CustomerCategory_Individual = "1";
	// public static String TIBCO_CustomerCategory_Corporate = "2";
	// public static String TIBCO_CustomerCategory_Bulk = "3";
	public static String TIBCO_CustomerCategory_Foreign = "4";
	public static String TIBCO_CustomerCategory_Outstation = "5";
	public static String TIBCO_MARITALSTATUS_SINGLE = "0";
	public static String TIBCO_MARITALSTATUS_MARRIED = "1";
	public static String TIBCO_MARITALSTATUS_Widowed = "2";
	public static String TIBCO_MARITALSTATUS_Divorced = "3";
	public static String TIBCO_MARITALSTATUS_Separated = "4";
	public static String TIBCO_ModeOfPayment_DebitCard = "5";
	public static String TIBCO_ModeOfPayment_ShopInvoice = "7";
	public static String TIBCO_ModeOfPayment_CreditCard = "4";
	public static String TIBCO_ModeOfPayment_Cheque = "3";
	public static String TIBCO_ModeOfPayment_DD = "2";
	public static String TIBCO_ModeOfPayment_Cash = "1";
	public static String TIBCO_ModeOfPayment_PrepaidInstrument = "6";
	public static String TIBCO_Occupation_Retired = "4";
	public static String TIBCO_Occupation_Others = "6";
	public static String TIBCO_Occupation_Housewife = "5";
	public static String TIBCO_Occupation_SelfEmployed = "3";
	public static String TIBCO_Occupation_Salaried = "2";
	public static String TIBCO_Occupation_Student = "1";
	public static String TIBCO_RelationshipType_M = "2";
	public static String TIBCO_RelationshipType_F = "1";
	public static String TIBCO_RelationshipType_G = "3";
	public static String TIBCO_RelationshipType_H = "4";
	// public static String TIBCO_Salutation_Company = "1003";
	public static String TIBCO_Salutation_Mrs = "Z001";
	public static String TIBCO_Salutation_Mr = "2";
	public static String TIBCO_Salutation_Ms = "1";
	public static String TIBCO_SubsIdentity_IMPU = "I2";
	public static String TIBCO_SubsIdentity_IMSI = "I1";
	public static String TIBCO_SubsIdentity_ICCID = "I9";
	public static String TIBCO_TypeofHouse_Rented = "RENTED";
	public static String TIBCO_TypeofHouse_Leased = "LEASED";
	public static String TIBCO_TypeofHouse_Owned = "OWNED";
	public static String TIBCO_Country_India = "IN";
	
	public static Map<String, Map<String,Map<String,Object>>> productPropMap;

	public static String TIBCO_ServiceType_LTEMobility = "Z0002";
	public static String TIBCO_ServiceType_LTEVoice = "Z0003";
	public static String TIBCO_ServiceType_LTEData = "Z0004";
	
	
	public static String TIBCO_IDENTIFICATIONTYPE_VISA="Z00004";
	public static String TIBCO_IDENTIFICATIONTYPE_AadhaarCard="Z00005";
	public static String TIBCO_IDENTIFICATIONTYPE_CustomerPictureURLId="Z00006";
	public static String TIBCO_IDENTIFICATIONTYPE_ElectionCommissionIDcard="Z00008";
	public static String TIBCO_IDENTIFICATIONTYPE_CGHSECHScard="Z00009";
	public static String TIBCO_IDENTIFICATIONTYPE_CertificateofAddresshavingphotoissuedbyMPorMLAorGazettedOfficerorTehsildaronletterhead="Z00010";
	public static String TIBCO_IDENTIFICATIONTYPE_Certificateofphotoidentity,issuedbyVillagePanchayatheadoritsequivalentauthorityforruralareas="Z00012";
	public static String TIBCO_IDENTIFICATIONTYPE_PhotoCreditcard="Z00013";
	public static String TIBCO_IDENTIFICATIONTYPE_SmartcardissuedbyCSD,DefenceParamilitary="Z00015";
	public static String TIBCO_IDENTIFICATIONTYPE_CurrentPassbookofPostOfficeanyscheduledbankhavingPhoto="Z00016";
	public static String TIBCO_IDENTIFICATIONTYPE_PhotoIdentitycardofCentralGovtPSUorStateGovtPSUonly="Z00017";
	public static String TIBCO_IDENTIFICATIONTYPE_PhotoIdentityCardissuedbyGovtrecognizededucationalinstitutionsforstudentsonly="Z00022";
	public static String TIBCO_IDENTIFICATIONTYPE_UIDAIletter="Z00023";
	public static String TIBCO_IDENTIFICATIONTYPE_RILEmployeeIdentityCard="Z00024";
	public static String TIBCO_IDENTIFICATIONTYPE_BankStatementPassbook="Z00025";
	public static String TIBCO_IDENTIFICATIONTYPE_PostOfficeAccountStatementPassbook="Z00026";
	public static String TIBCO_IDENTIFICATIONTYPE_RationCard="Z00027";
	public static String TIBCO_IDENTIFICATIONTYPE_VoterID="Z00028";
	public static String TIBCO_IDENTIFICATIONTYPE_GovernmentPhotoIDcardsservicephotoidentitycardissuedbyPSU="Z00029";
	public static String TIBCO_IDENTIFICATIONTYPE_ElectricityBillnotolderthan3months="Z00030";
	public static String TIBCO_IDENTIFICATIONTYPE_Waterbillnotolderthan3months="Z00031";
	public static String TIBCO_IDENTIFICATIONTYPE_TelephoneLandlineBillnotolderthan3months="Z00032";
	public static String TIBCO_IDENTIFICATIONTYPE_PropertyTaxReceiptnotolderthan3months="Z00033";
	public static String TIBCO_IDENTIFICATIONTYPE_CreditCardStatementnotolderthan3months="Z00034";
	public static String TIBCO_IDENTIFICATIONTYPE_InsurancePolicy="Z00035";
	public static String TIBCO_IDENTIFICATIONTYPE_SignedLetterhavingPhotofromBankonletterhead="Z00036";
	public static String TIBCO_IDENTIFICATIONTYPE_SignedLetterhavingPhotoissuedbyregisteredCompanyonletterhead="Z00037";
	public static String TIBCO_IDENTIFICATIONTYPE_SignedLetterhavingPhotoissuedbyRecognizedEducationalInstructiononletterhead="Z00038";
	public static String TIBCO_IDENTIFICATIONTYPE_NREGSJobCard="Z00039";
	public static String TIBCO_IDENTIFICATIONTYPE_ArmsLicense="Z00040";
	public static String TIBCO_IDENTIFICATIONTYPE_PensionerCard="Z00041";
	public static String TIBCO_IDENTIFICATIONTYPE_FreedomFighterCard="Z00042";
	public static String TIBCO_IDENTIFICATIONTYPE_havingPhotoPOI="Z00043";
	public static String TIBCO_IDENTIFICATIONTYPE_PensionerCardhavingphoto="Z00044";
	public static String TIBCO_IDENTIFICATIONTYPE_RegisteredSaleLeaseRentAgreement="Z00045";
	public static String TIBCO_IDENTIFICATIONTYPE_AddressCardhavingPhotoissuedbyDepartmentofPosts="Z00046";
	public static String TIBCO_IDENTIFICATIONTYPE_CasteandDomicileCertificatehavingPhotoissuedbyStateGovt="Z00047";
	public static String TIBCO_IDENTIFICATIONTYPE_DisabilityIDCardhandicappedmedicalcertificateissuedbytherespectiveStateUTGovernmentsAdministrations="Z00048";
	public static String TIBCO_IDENTIFICATIONTYPE_GasConnectionBillnotolderthan3months="Z00049";
	public static String TIBCO_IDENTIFICATIONTYPE_PassportofSpouse="Z00050";
	public static String TIBCO_IDENTIFICATIONTYPE_PassportofParentsincaseofMinor="Z00051";


	/**
	 * 将该类中的静态变量封装成终端要用的格式。 [{MARITALSTATUS={Divorced=3, SINGLE=0, Separated=4,
	 * MARRIED=1, Widowed=2}}, {SubsIdentity={IMSI=I1, ICCID=I9, IMPU=I2}}]
	 */
	public static List returnDicForTerm() {
		Map all = extract();
		List rm = new ArrayList();
		Set keys = all.keySet();
		for (Iterator it = keys.iterator(); it.hasNext();) {
			String key = (String) it.next();
			Map m = new HashMap();
			m.put(key, all.get(key));
			rm.add(m);
		}
		// System.err.println(rm);
		return rm;
	}

	/**
	 * 将该类中的静态变量封装成终端要用的格式。 {{MARITALSTATUS={MARITALSTATUS={Divorced=3,
	 * SINGLE=0}}}, {SubsIdentity{SubsIdentity={IMSI=I1, IMPU=I2}}}}
	 * 
	 * @return
	 */
	public static Map returnDicMapForTerm() {
		Map all = extract();
		return all;
	}

	private static Map extract() {
		Field[] df = TibcoConstant.class.getDeclaredFields();
		Map all = new HashMap();
		for (int i = 0; i < df.length; i++) {
			Field f = df[i];
			String fieldName = f.getName();
			String[] fns = fieldName.split("_");
			if (fns[0].equals("TIBCO")) {
				Map m;
				if (all.containsKey(fns[1])) {
					m = (Map) all.get(fns[1]);
				} else {
					m = new HashMap();
					all.put(fns[1], m);
//					all.put(m, fns[1]);
				}
				try {
					m.put(f.get(TibcoConstant.class),fns[2] );
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return all;
	}

}
