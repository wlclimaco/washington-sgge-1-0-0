package com.prosperitasglobal.sendsolv.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The MemberAPIController Class.
 *
 * @author Flavio Tosta, Washington Costa
 *
 */
@Controller
@RequestMapping("/api/member")
public class MemberAPIController extends MemberBaseController
{

	// /** The Constant APPLY_STATUS. */
	// private static final String APPLY_STATUS = "applyStatus";
	// /** The URL mapping constants. */
	// private static final String DELETE_MEMBER = "/delete";
	//
	// /** The Constant EDIT_MEMBER. */
	// private static final String EDIT_MEMBER = "/update";
	//
	// /** The Constant FETCH_ALL. */
	// private static final String FETCH_ALL = "/fetchall";
	//
	// /** The Constant INSERT_MEMBER. */
	// private static final String INSERT_MEMBER = "/insert";
	//
	// /** The Constant FETCH. */
	// private static final String FETCH = "/fetch";
	//
	// /**
	// * Fetch all Members.
	// *
	// * @param memberInquiryRequest the member inquiry request
	// * @return the response
	// */
	// @RequestMapping(value = FETCH_ALL, method = RequestMethod.POST)
	// @ResponseBody
	// public MemberResponse fetchAll(@RequestBody MemberInquiryRequest memberInquiryRequest)
	// {
	//
	// return fetchMemberByRequest(memberInquiryRequest);
	//
	// }
	//
	// /**
	// * Fetch.
	// *
	// * @param fetchByIdRequest the fetch by id request
	// * @return the member response
	// */
	// @RequestMapping(value = FETCH, method = RequestMethod.POST)
	// @ResponseBody
	// public MemberResponse fetch(@RequestBody FetchByIdRequest fetchByIdRequest)
	// {
	// return fetchMemberById(fetchByIdRequest);
	//
	// }
	//
	// /**
	// * Edit one member.
	// *
	// * @param memberRequest the member request
	// * @return the response
	// */
	// @RequestMapping(value = EDIT_MEMBER, method = RequestMethod.POST)
	// @ResponseBody
	// public MemberResponse edit(@RequestBody MemberMaintenanceRequest memberRequest)
	// {
	// return editMember(memberRequest);
	//
	// }
	//
	// /**
	// * Delete one member.
	// *
	// * @param memberRequest the member request
	// * @return the response
	// */
	// @RequestMapping(value = DELETE_MEMBER, method = RequestMethod.POST)
	// @ResponseBody
	// public MemberResponse delete(@RequestBody MemberMaintenanceRequest memberRequest)
	// {
	//
	// return deleteMember(memberRequest);
	//
	// }
	//
	// /**
	// * Insert one member.
	// *
	// * @param memberRequest the member request
	// * @return the response
	// */
	// @RequestMapping(value = INSERT_MEMBER, method = RequestMethod.POST)
	// @ResponseBody
	// public MemberResponse insert(@RequestBody MemberMaintenanceRequest memberRequest)
	// {
	//
	// return insertMember(memberRequest);
	//
	// }
	//
	// /**
	// * Apply the status change to member.
	// *
	// * @param memberRequest the member request
	// * @return the member response
	// */
	// @RequestMapping(value = APPLY_STATUS, method = RequestMethod.POST)
	// @ResponseBody
	// public MemberResponse apply(@RequestBody MemberMaintenanceRequest memberRequest)
	// {
	//
	// return applyStatus(memberRequest);
	//
	// }
}
