<<<<<<< HEAD
SMS
===

Final sms reader
>>>>>>> d371de21646415547ce88d6c72684858744e3d41

=Reading categories=
call startINI(fcall); first
then:
	function fcall() {
	showContents() ;
	alert("and hopefully closing");
	//tests
	alert(INIget("categories","Food"));
	alert(INIget("categories","Travel"));
	alert(INIget("categories","Entertainment"));
	alert(INIget("categories","Other"));
	alert(INIget("categories","Telecommunications"));
	
	
	}