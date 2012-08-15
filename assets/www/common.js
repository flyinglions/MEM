//Global var for Phonegap ready
var isPhoneGapReady = false;

//global var to check if app is runned for first time: few events should run then
var firstRun = true;

//Phone types
var isAndroid = false;
var isBlackberry = false;

//Device's uuid
var deviceUUID;

function init()
{
	if(isPhoneGapReady)
		{
			onDeviceReady();
		}
	else
		{
			document.addEventListener("deviceready", onDeviceReady, false);
		}
	
}

function onDeviceReady()
{
	isPhoneGapReady = true;
	
	isFirstRun = true;
	
	//Get device UUID
	deviceUUID = device.uuid;
	
	//Detect device's platform
	deviceDetection();
	
	//Any startup events
	executeStartupEvents();
	
	//Any events that needs to run if app is opened for first time
	//executeFirstRunEvents();
	
	
	
}

function deviceDetection()
{
	if(isPhoneGapReady)
		{
			switch(device.platform)
			{
				case "Android":
					isAndroid = true;
					break;
				case "Blackberry":
					isBlacberry = true;
					break;
			}
		
		
		}

}

function executeStartupEvents(){
	if(isPhoneGapReady)
		{
			   document.addEventListener("resume", onResume, false);
			   document.addEventListener("pause", onPause, false);
			   //document.addEventListener("backbutton", backKeyDown, false);
			
			     
			   	window.plugins.SMSReceiverPlugin.register("smscallback", function()
					{
					    console.log("Registration ok");
					}, function (e)
					{
					    console.log("Registration NOT ok: " + e);
					});
					
		   
				function onResume(buttonIndex) {
					if(isPhoneGapReady == false){
						init();
					}
					else{
						//alert('Resuming');
						console.log("Resuming");
					}
				    
				}   
				
				function onPause() {
				    isPhoneGapReady = false;
				    //console.log("PAAAAAAAAAAAAAAAAAAUUUUSSSSSSSSSSSSEEEE");
				} 		
				
				function backKeyDown(e) {
				    isPhoneGapReady = false;
				    //e.preventDefault();
				    //onPause();
				    //console.log("BAAAAAAAAAAAAACCCCCCCCCCCCKKKKKKKKKKKKK");
				    //navigator.app.backHistory();
				    //window.history.back();
				} 				
				
			   
			   /*btnList.onclick = function() {
				   window.plugins.SMSReader.getInbox("",
					function(data){
					      
					      var text = getSMSData(data);
					      console.log(text);
					      document.getElementById("inbox").innerHTML = text;
					      
					      
					},
					function(e){
						console.log(e);
					});
			    }
			   
			  
			   
			   btnList.disabled=false;	*/
			
			
		}

}

function executeFirstRunEvents(){
	
	if(firstRun){
   	navigator.notification.confirm(
		    'Do you want to import inbox?',  // message
		    onImportConfirm,         // callback
		    'First RUN: Import inbox',            // title
		    'Yes, Cancel'                  // buttons
			);
			
			function onImportConfirm(buttonIndex) {
			    //alert('You selected button ' + buttonIndex);
				if(buttonIndex == '1'){
					   window.plugins.SMSReader.getInbox("",
								function(data){
								      var text = getSMSData(data);
								      console.log(text);
								      document.getElementById("inbox").innerHTML = text;
								      
								},
								function(e){
									console.log(e);
								});
				}
				
			}	
			
			firstRun = false;
			
	}
	
}

function notificationCallback(){
	if(isPhoneGapReady == false){
		window.plugins.StatusBarNotification.notify("Decoded SMS", "New Decoded msg");
	}
	
	
}

/*window.onbeforeunload  =  function(e)
{
	window.plugins.SMSReceiverPlugin.unregister(null, null); 
}*/

window.onload = init;
