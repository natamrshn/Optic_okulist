const contactIcons = document.querySelectorAll('.feedback-button__icon');

const ContactsIcons = {
  pressed: false,

  toggle() {
    [...contactIcons].forEach(elem => elem.classList.toggle('feedback-button__icon--show'));

    this.pressed = !this.pressed;
  },

  handleDocumentClick: function(event) {
    const menuIconBtn = document.querySelector('.feedback-button__icon-message');
    const targetElement = event.target;
  
    if (this.pressed && targetElement !== menuIconBtn && !menuIconBtn.contains(targetElement)) {
      this.toggle();
    }
  }
}

document.addEventListener('click', ContactsIcons.handleDocumentClick.bind(ContactsIcons));
// Функція перемикання кнопок на сторінці реєстрації/входу
// eslint-disable-next-line no-unused-vars
function showForm(formName) {
  const allForms = document.querySelectorAll('.form-container');
  allForms.forEach(form => form.classList.remove('active'));

  const selectedForm = document.querySelector(`.form-container--${formName}`);
  selectedForm.classList.add('active');

  const allTabs = document.querySelectorAll('.form-tab');
  allTabs.forEach(tab => tab.classList.remove('form-tab--active'));

  const selectedTab = document.querySelector(`.form-tab[data-form="${formName}"]`);
  selectedTab.classList.add('form-tab--active');
}
