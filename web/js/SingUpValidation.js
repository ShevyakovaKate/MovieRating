
function showError(container, errorMessage) {
    container.className = 'error';
    var msgElem = document.createElement('span');
    msgElem.className = "error-message";
    msgElem.innerHTML = errorMessage;
    container.appendChild(msgElem);
}

function resetError(container) {
    container.className = '';
    if (container.lastChild.className == "error-message") {
        container.removeChild(container.lastChild);
    }
}

function validate(form) {
    var elements = form.elements;

    /*resetError(elements.email.parentNode);
    if (!elements.email.value) {
        showError(elements.from.parentNode, ' Enter email.');
    }*/

    resetError(elements.password.parentNode);
    /*if (!elements.password.value) {
        showError(elements.password.parentNode, ' Enter pаssword.');
    }
    else*/ if (elements.password.value != elements.passwordConfirm.value) {
        showError(elements.password.parentNode, ' Password mismatch.');
        return false;
    }
    return true;

    /*resetError(elements.name.parentNode);
    if (!elements.name.value) {
        showError(elements.name.parentNode, ' Enter username.');
    }*/
}
