<div id="action-dialog-panel" class="action-dialog">
	<form method="post" action="">
	       <fieldset class="two-line create-action-container" id="">
			<ul>
	           	<li>
	               	<label for="mac_address">
	               		Enter Mac Address <span class="required-small">*Required</span>
	               		<label><br>
	                   		<input type="text" value="11:11:11:11:11:" class="mac" id="mac_address"><span class="validate-icon icon-small icn-notice-form hide" id="mac_address_validate_icon"></span>
	               		</label>
	               	</label>
	            </li>
	            <li class="floating-form-info hide" >
	                <div class="point-detail-container highlight">
	                    <dl>
	                        <dt>Device Type:</dt>
	                        <dd id="device-type"></dd>
	                        <dt>Device ID:</dt>
	                        <dd id="device-id"></dd>
	                        <dt>Manufacture:</dt>
	                        <dd id="manufacture"></dd>
	                        <dt>Model Number:</dt>
	                        <dd id="model-number"></dd>
	                    </dl>
	                </div>            
                </li>
				<li class="add-fields hide">
					<ul>
	                   	<li class="highlight">We were unable to locate device “<span id="mac_id">11:11:11:11:11</span>”. Please complete the fields below and we will add and connect this device.</li>
	                   	<li>
	                           <label for="">Device Type: <span class="required-small">*</span><label><br>
	                           <select id="select-device-types"></select>
						</label></label></li>
	                   	<li>
	                           <label for="">Device ID: <span class="required-small">*</span><label><br>
	                           <input type="text" class="" id="device_name_input" value="">
						</label></label></li>
	                   	<li>
	                           <label for="">Manufacture: <span class="required-small">*</span><label><br>
	                           <select disabled="" id="select-manufacture"><option>Select</option></select>
						</label></label></li>
	                       <li>
	                           <label for="">Model Number <span class="required-small">*</span><label><br>
	                           <select disabled="" id="select-model-number"><option>Select</option></select>
	                       </label></label></li>
	                   </ul>
	               </li>
	           	<li>
	               	<label for="install_code">Enter Install Code<span class="required">*</span><label><br>
	                   <input type="text" id="install_code" value="">
	               </label></label></li>
	           </ul>
	        </fieldset>		
	</form>
	