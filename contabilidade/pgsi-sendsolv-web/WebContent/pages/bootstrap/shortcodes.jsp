<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>



<div class="main">

	<div class="main-inner">

	    <div class="container">

	      <div class="row">

	      	<div class="span12">

	      		<div class="widget ">

	      			<div class="widget-header">
	      				<i class="icon-user"></i>
	      				<h3>Your Account</h3>
	  				</div> <!-- /widget-header -->

					<div class="widget-content">



						<div class="tabbable">
						<ul class="nav nav-tabs">
						  <li>
						    <a href="#formcontrols" data-toggle="tab">Form Controls</a>
						  </li>
						  <li  class="active"><a href="#jscontrols" data-toggle="tab">JS Controls</a></li>
						</ul>

						<br>

							<div class="tab-content">
								<div class="tab-pane" id="formcontrols">
								<form id="edit-profile" class="form-horizontal">
									<fieldset>

										<div class="control-group">
											<label class="control-label" for="username">Username</label>
											<div class="controls">
												<input type="text" class="span6 disabled" id="username" value="Example" disabled>
												<p class="help-block">Your username is for logging in and cannot be changed.</p>
											</div> <!-- /controls -->
										</div> <!-- /control-group -->


										<div class="control-group">
											<label class="control-label" for="firstname">First Name</label>
											<div class="controls">
												<input type="text" class="span6" id="firstname" value="John">
											</div> <!-- /controls -->
										</div> <!-- /control-group -->


										<div class="control-group">
											<label class="control-label" for="lastname">Last Name</label>
											<div class="controls">
												<input type="text" class="span6" id="lastname" value="Donga">
											</div> <!-- /controls -->
										</div> <!-- /control-group -->


										<div class="control-group">
											<label class="control-label" for="email">Email Address</label>
											<div class="controls">
												<input type="text" class="span4" id="email" value="john.donga@egrappler.com">
											</div> <!-- /controls -->
										</div> <!-- /control-group -->


										<br /><br />

										<div class="control-group">
											<label class="control-label" for="password1">Password</label>
											<div class="controls">
												<input type="password" class="span4" id="password1" value="thisispassword">
											</div> <!-- /controls -->
										</div> <!-- /control-group -->


										<div class="control-group">
											<label class="control-label" for="password2">Confirm</label>
											<div class="controls">
												<input type="password" class="span4" id="password2" value="thisispassword">
											</div> <!-- /controls -->
										</div> <!-- /control-group -->



                                        <div class="control-group">
											<label class="control-label">Checkboxes</label>


                                            <div class="controls">
                                            <label class="checkbox inline">
                                              <input type="checkbox"> Option 01
                                            </label>

                                            <label class="checkbox inline">
                                              <input type="checkbox"> Option 02
                                            </label>
                                          </div>		<!-- /controls -->
										</div> <!-- /control-group -->



                                        <div class="control-group">
											<label class="control-label">Radio Buttons</label>


                                            <div class="controls">
                                            <label class="radio inline">
                                              <input type="radio"  name="radiobtns"> Option 01
                                            </label>

                                            <label class="radio inline">
                                              <input type="radio" name="radiobtns"> Option 02
                                            </label>
                                          </div>	<!-- /controls -->
										</div> <!-- /control-group -->




                                        <div class="control-group">
											<label class="control-label" for="radiobtns">Combined Textbox</label>

                                            <div class="controls">
                                               <div class="input-prepend input-append">
                                                  <span class="add-on">$</span>
                                                  <input class="span2" id="appendedPrependedInput" type="text">
                                                  <span class="add-on">.00</span>
                                                </div>
                                              </div>	<!-- /controls -->
										</div> <!-- /control-group -->





                                        <div class="control-group">
											<label class="control-label" for="radiobtns">Textbox with Buttons </label>

                                            <div class="controls">
                                               <div class="input-append">
                                                  <input class="span2 m-wrap" id="appendedInputButton" type="text">
                                                  <button class="btn" type="button">Go!</button>
                                                </div>
                                              </div>	<!-- /controls -->
										</div> <!-- /control-group -->





                                        <div class="control-group">
											<label class="control-label" for="radiobtns">Dropdown in a button group</label>

                                            <div class="controls">
                                              <div class="btn-group">
                                              <a class="btn btn-primary" href="#"><i class="icon-user icon-white"></i> User</a>
                                              <a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" href="#"><span class="caret"></span></a>
                                              <ul class="dropdown-menu">
                                                <li><a href="#"><i class="icon-pencil"></i> Edit</a></li>
                                                <li><a href="#"><i class="icon-trash"></i> Delete</a></li>
                                                <li><a href="#"><i class="icon-ban-circle"></i> Ban</a></li>
                                                <li class="divider"></li>
                                                <li><a href="#"><i class="i"></i> Make admin</a></li>
                                              </ul>
                                            </div>
                                              </div>	<!-- /controls -->
										</div> <!-- /control-group -->





                                        <div class="control-group">
											<label class="control-label" for="radiobtns">Button sizes</label>

                                            <div class="controls">
                                              <a class="btn btn-large" href="#"><i class="icon-star"></i> Star</a>
                                                <a class="btn btn-small" href="#"><i class="icon-star"></i> Star</a>
                                                <a class="btn btn-mini" href="#"><i class="icon-star"></i> Star</a>
                                              </div>	<!-- /controls -->
										</div> <!-- /control-group -->



										 <br />


										<div class="form-actions">
											<button type="submit" class="btn btn-primary">Save</button>
											<button class="btn">Cancel</button>
										</div> <!-- /form-actions -->
									</fieldset>
								</form>
								</div>

								<div class="tab-pane active" id="jscontrols">
									<form id="edit-profile2" class="form-vertical">
										<fieldset>





											<div class="control-group">
												<label class="control-label">Alerts</label>
												<div class="controls">
													 <div class="alert">
                                              <button type="button" class="close" data-dismiss="alert">&times;</button>
                                              <strong>Warning!</strong> Best check yo self, you're not looking too good.
                                            </div>


                                                <div class="alert alert-success">
                                                  <button type="button" class="close" data-dismiss="alert">&times;</button>
                                                  <strong>Warning!</strong> Best check yo self, you're not looking too good.
                                                </div>


                                                     <div class="alert alert-info">
                                              <button type="button" class="close" data-dismiss="alert">&times;</button>
                                              <strong>Warning!</strong> Best check yo self, you're not looking too good.
                                            </div>







                                                     <div class="alert alert-block">
                                                      <button type="button" class="close" data-dismiss="alert">&times;</button>
                                                      <h4>Warning!</h4>
                                                      Best check yo self, you're not...
                                                    </div>
												</div> <!-- /controls -->





											</div> <!-- /control-group -->




                                            <div class="control-group">
												<label class="control-label">Progress Bar</label>
												<div class="controls">
													 <div class="progress">
                                                      <div class="bar" style="width: 60%;"></div>
                                                    </div>


                                                    <div class="progress progress-striped">
                                                      <div class="bar" style="width: 20%;"></div>
                                                    </div>


                                                    <div class="progress progress-striped active">
                                                      <div class="bar" style="width: 40%;"></div>
                                                    </div>
												</div> <!-- /controls -->
											</div> <!-- /control-group -->







                                            <div class="control-group">
												<label class="control-label">Accordion</label>
												<div class="controls">

													 <div class="accordion" id="accordion2">
                                                      <div class="accordion-group">
                                                        <div class="accordion-heading">
                                                          <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
                                                            Collapsible Group Item #1
                                                          </a>
                                                        </div>
                                                        <div id="collapseOne" class="accordion-body collapse in">
                                                          <div class="accordion-inner">
                                                            It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).


                                                          </div>
                                                        </div>
                                                      </div>
                                                      <div class="accordion-group">
                                                        <div class="accordion-heading">
                                                          <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo">
                                                            Collapsible Group Item #2
                                                          </a>
                                                        </div>
                                                        <div id="collapseTwo" class="accordion-body collapse">
                                                          <div class="accordion-inner">
                                                            Anim pariatur cliche...
                                                          </div>
                                                        </div>
                                                      </div>
                                                    </div>
												</div> <!-- /controls -->
											</div> <!-- /control-group -->






                                            <div class="control-group">
												<label class="control-label">Progress Bar</label>
												<div class="controls">
													 <!-- Button to trigger modal -->
                                                    <a href="#myModal" role="button" class="btn" data-toggle="modal">Launch demo modal</a>

                                                    <!-- Modal -->
                                                    <div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                      <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                                        <h3 id="myModalLabel">Thank you for visiting EGrappler.com</h3>
                                                      </div>
                                                      <div class="modal-body">
                                                        <p>One fine body…</p>
                                                      </div>
                                                      <div class="modal-footer">
                                                        <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
                                                        <button class="btn btn-primary">Save changes</button>
                                                      </div>
                                                    </div>
												</div> <!-- /controls -->
											</div> <!-- /control-group -->

















                                             <div class="control-group">
												<label class="control-label">Social Buttons</label>
												<div class="controls">
													 <button class="btn btn-facebook-alt"><i class="icon-facebook-sign"></i> Facebook</button>
                                                    <button class="btn btn-twitter-alt"><i class="icon-twitter-sign"></i> Twitter</button>
                                                    <button class="btn btn-google-alt"><i class="icon-google-plus-sign"></i> Google+</button>
                                                    <button class="btn btn-linkedin-alt"><i class="icon-linkedin-sign"></i> Linked In</button>
                                                    <button class="btn btn-pinterest-alt"><i class="icon-pinterest-sign"></i> Pinterest</button>
                                                    <button class="btn btn-github-alt"><i class="icon-github-sign"></i> Github</button>
												</div> <!-- /controls -->
											</div> <!-- /control-group -->

											<br />

											<div class="form-actions">
												<button type="submit" class="btn btn-primary">Save</button> <button class="btn">Cancel</button>
                                                <button class="btn btn-info">Info</button>
                                                <button class="btn btn-danger">Danger</button>
                                                <button class="btn btn-warning">Warning</button>
                                                <button class="btn btn-invert">Invert</button>
                                                <button class="btn btn-success">Success</button>
											</div>
										</fieldset>
									</form>
								</div>

							</div>


						</div>





					</div> <!-- /widget-content -->

				</div> <!-- /widget -->

		    </div> <!-- /span8 -->




	      </div> <!-- /row -->

	    </div> <!-- /container -->

	</div> <!-- /main-inner -->

</div> <!-- /main -->




<div class="extra">

	<div class="extra-inner">

		<div class="container">

			<div class="row">
                    <div class="span3">
                        <h4>
                            About Free Admin Template</h4>
                        <ul>
                            <li><a href="javascript:;">EGrappler.com</a></li>
                            <li><a href="javascript:;">Web Development Resources</a></li>
                            <li><a href="javascript:;">Responsive HTML5 Portfolio Templates</a></li>
                            <li><a href="javascript:;">Free Resources and Scripts</a></li>
                        </ul>
                    </div>
                    <!-- /span3 -->
                    <div class="span3">
                        <h4>
                            Support</h4>
                        <ul>
                            <li><a href="javascript:;">Frequently Asked Questions</a></li>
                            <li><a href="javascript:;">Ask a Question</a></li>
                            <li><a href="javascript:;">Video Tutorial</a></li>
                            <li><a href="javascript:;">Feedback</a></li>
                        </ul>
                    </div>
                    <!-- /span3 -->
                    <div class="span3">
                        <h4>
                            Something Legal</h4>
                        <ul>
                            <li><a href="javascript:;">Read License</a></li>
                            <li><a href="javascript:;">Terms of Use</a></li>
                            <li><a href="javascript:;">Privacy Policy</a></li>
                        </ul>
                    </div>
                    <!-- /span3 -->
                    <div class="span3">
                        <h4>
                            Open Source jQuery Plugins</h4>
                        <ul>
                            <li><a href="http://www.egrappler.com">Open Source jQuery Plugins</a></li>
                            <li><a href="http://www.egrappler.com;">HTML5 Responsive Tempaltes</a></li>
                            <li><a href="http://www.egrappler.com;">Free Contact Form Plugin</a></li>
                            <li><a href="http://www.egrappler.com;">Flat UI PSD</a></li>
                        </ul>
                    </div>
                    <!-- /span3 -->
                </div> <!-- /row -->

		</div> <!-- /container -->

	</div> <!-- /extra-inner -->

</div> <!-- /extra -->


>
