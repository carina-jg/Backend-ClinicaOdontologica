window.addEventListener('load', function () {

(function(){
  event.preventDefault();
  const url = '/pacientes';
  const settings = {
    method: 'GET'
}
  fetch(url,settings)
  .then(response => response.json())
  .then(data => {
     for(paciente of data){

       let deleteButton = '<button' +
                                  ' id=' + '\"' + 'btn_delete_' + paciente.id + '\"' +
                                  ' type="button" onclick="deleteBy('+paciente.id+')" class="btn btn-danger btn_delete">' +
                                  '&times' +
                                  '</button>';

      let get_More_Info_Btn = '<button' +
                                  ' id=' + '\"' + 'btn_id_' + paciente.id + '\"' +
                                  ' type="button" onclick="findBy('+paciente.id+')" class="btn btn-info btn_id">' +
                                  paciente.id +
                                  '</button>';

      let tr_id = 'tr_' + paciente.id;
      let pacienteRow = '<tr id=\"' + tr_id + "\"" + '>' +
                      '<td>' + get_More_Info_Btn + '</td>' +
                      '<td class=\"td_nombre\">' + paciente.nombre + '</td>' +
                      '<td class=\"td_apellido\">' + paciente.apellido + '</td>' +
                       '<td class=\"td_dni\">' + paciente.dni + '</td>' +
                        '<td class=\"td_fechaIngreso\">' + paciente.fechaIngreso + '</td>' +
                      '<td>' + deleteButton + '</td>' +
                      '</tr>';
            $('#pacienteTable tbody').append(pacienteRow);
          };

})
})

(function(){
  let pathname = window.location.pathname;
  if (pathname == "/pacientes.html") {
      document.querySelector(".nav .nav-item a:last").addClass("active");
  }
})


})