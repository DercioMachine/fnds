$(document).ready(function(){
        $('#comboboxProvincias').on('change',function(){

          var provinciaId = $(this).val();

          $.ajax({
          type: 'GET',
          url: 'http://localhost:8081/distrito/'+provinciaId,
           success: function (result){
           var result = JSON.parse(result);
           var s = '';
          for(var i=0; i< result.length; i++){
              s+='<option value="'+result[i].id+'">'+result[i].designacao+'</option>';
          }
        $('#comoboxDistrito').html(s);
    }
});


        });
    });


          
$(document).ready(function(){
    $('#comoboxDistrito').on('change',function(){

      var distritoId = $(this).val();

     // alert(distritoId);

      $.ajax({
      type: 'GET',
      url: 'http://localhost:8081/posto/'+distritoId,
       success: function (result){
       var result = JSON.parse(result);
       var s = '';
      for(var i=0; i< result.length; i++){
          s+='<option value="'+result[i].id+'">'+result[i].designacao+'</option>';
      }
    $('#comboboxPosto').html(s);
}
});


    });
});


function onChangeStatus(){
		var status = document.getElementById("origem");
		
		if(status.value=="individual"){
			
			document.getElementById("nome").style.display="block";
			document.getElementById("genero").style.display="block";
			document.getElementById("comunicacao").style.display="block";
			document.getElementById("contacto").style.display="block";
			document.getElementById("faixaEtaria").style.display="block";
			document.getElementById("endereco").style.display="block";
			document.getElementById("email").style.display="block";
			
			document.getElementById("nrHomens").style.display="none";
			document.getElementById("nrMulheres").style.display="none";
			document.getElementById("nomeGrupo").style.display="none";
			
			
			
			
		}else if(status.value=="grupo"){
			
			document.getElementById("nome").style.display="block";
			document.getElementById("comunicacao").style.display="block";
			document.getElementById("contacto").style.display="block";
			document.getElementById("endereco").style.display="block";
			document.getElementById("email").style.display="block";
			document.getElementById("nrHomens").style.display="block";
			document.getElementById("nrMulheres").style.display="block";
			document.getElementById("nomeGrupo").style.display="block";
			
			document.getElementById("faixaEtaria").style.display="none";
			document.getElementById("genero").style.display="none";
			
			
		}else if(status.value=="anonimo"){
			
			document.getElementById("genero").style.display="block";
			document.getElementById("comunicacao").style.display="block";
			document.getElementById("contacto").style.display="block";
			document.getElementById("faixaEtaria").style.display="block";
			document.getElementById("endereco").style.display="block";
			document.getElementById("email").style.display="block";
			
			document.getElementById("nrHomens").style.display="none";
			document.getElementById("nrMulheres").style.display="none";
			document.getElementById("nomeGrupo").style.display="none";
			document.getElementById("nome").style.display="none";
		}else{
			
			document.getElementById("nrHomens").style.display="none";
			document.getElementById("nrMulheres").style.display="none";
			document.getElementById("nomeGrupo").style.display="none";
			document.getElementById("nome").style.display="none";
			document.getElementById("genero").style.display="none";
			document.getElementById("comunicacao").style.display="none";
			document.getElementById("contacto").style.display="none";
			document.getElementById("faixaEtaria").style.display="none";
			document.getElementById("endereco").style.display="none";
			document.getElementById("email").style.display="none";
			
		}
	}
      
      
      
      
	
	
	
	

	
	


	

	
                

                
                