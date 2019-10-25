function muitoCurto(campo, nome, tamanho) {
    if (campo.value.length >= tamanho) return false;
    alert("O conteúdo do campo '" + nome
            + "' deveria ter pelo menos " + tamanho + " caracteres."
            + " Por favor, preencha-o corretamente.");
    return true;
}

function tamanhoErrado(campo, nome, tamanho) {
    if (campo.value.length === tamanho) return false;
    alert("O conteúdo do campo '" + nome
            + "' deveria ter exatamente " + tamanho + " caracteres. "
            + "Por favor, preencha-o corretamente.");
    return true;
}

function naoNumerico(campo, nome) {
    if (!isNaN(campo.value)) return false;
    alert("Digite somente números no campo '" + nome + "', por favor.");
    return true;
}

function diferentes(campo1, nome1, campo2, nome2) {
    if (campo1.value === campo2.value) return false;
    alert("Os campos '" + nome1 + "' e '" + nome2 + "' devem ser iguais.");
    return true;
}

function validarFormulario() {
    var cad = document.getElementById('cad');

    if (muitoCurto(cad.txtnome, 'Nome', 2)) return;
    if (muitoCurto(cad.txtsobrenome, 'Sobrenome', 2)) return;
    if (tamanhoErrado(cad.txtCPF, 'CPF', 11)) return;
    if (tamanhoErrado(cad.txtDDD, 'DDD', 2)) return;
    if (muitoCurto(cad.txtContato, 'Nº do telefone', 8)) return;
    if (muitoCurto(cad.txtEmail1, 'E-mail', 10)) return;
    if (muitoCurto(cad.txtRua, 'Logradouro', 3)) return;
    if (muitoCurto(cad.txtBairro, 'Bairro', 3)) return;
    if (muitoCurto(cad.txtCidade, 'Cidade', 3)) return;
    if (muitoCurto(cad.txtNumero, 'Número do endereço', 1)) return;
    if (tamanhoErrado(cad.txtCep, 'CEP', 8)) return;
    if (muitoCurto(cad.txtLogin, 'Nome de usuário', 7)) return;
    if (muitoCurto(cad.txtsenha, 'Senha', 6)) return;
    if (muitoCurto(cad.txtCsenha, 'Confirmação da senha', 6)) return;
    if (naoNumerico(cad.txtCPF, 'CPF')) return;
    if (naoNumerico(cad.txtDDD, 'DDD')) return;
    if (naoNumerico(cad.txtContato, 'Nº do telefone')) return;
    //if (naoNumerico(cad.txtNumero, 'Número')) return;
    if (naoNumerico(cad.txtCep, 'CEP')) return;

    if (diferentes(cad.txtsenha, 'Senha', cad.txtCsenha, 'Confirmação da Senha')) return;
    if (diferentes(cad.txtEmail1, 'E-mail', cad.txtEmail2, 'Confirmação de E-mail')) return;

    if (cad.txtdata_nasc.value.length !== 10) {
        alert("Formato de 'data de nascimento' inválido."
                + " Por favor, preencha-o corretamente.");
        return;
    }

    if (!verificarCPF(cad.txtCPF.value)) {
        alert("Os dígitos verificadores do CPF não conferem.");
        return;  
    }

    cad.submit();
}

function verificarCPF(strCpf) {
    if (!/[0-9]{11}/.test(strCpf)) return false;
    if (strCpf === "00000000000") return false;

    var soma = 0;

    for (var i = 1; i <= 9; i++) {
        soma += parseInt(strCpf.substring(i - 1, i)) * (11 - i);
    }

    var resto = soma % 11;

    if (resto === 10 || resto === 11 || resto < 2) {
        resto = 0;
    } else {
        resto = 11 - resto;
    }

    if (resto !== parseInt(strCpf.substring(9, 10))) {
        return false;
    }

    soma = 0;

    for (var i = 1; i <= 10; i++) {
        soma += parseInt(strCpf.substring(i - 1, i)) * (12 - i);
    }
    resto = soma % 11;

    if (resto === 10 || resto === 11 || resto < 2) {
        resto = 0;
    } else {
        resto = 11 - resto;
    }
 
    if (resto !== parseInt(strCpf.substring(10, 11))) {
        return false;
    }

    return true;
}