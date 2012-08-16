//Global var for Phonegap ready
var isPhoneGapReady = false;

//global var to check if app is runned for first time: few events should run then
var firstRun = true;


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
	
	//Any startup events
	executeStartupEvents();
	

}

window.onload = init;


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


