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

function changeImage(imageNumber) {
  const productImageSmall = document.querySelectorAll('.product__image--small');
  const productEllipses = document.querySelectorAll('.product__ellipse');
  let isChanged = true;
  const clickedImage = document.querySelector(`.product__image--small--${imageNumber}`);

  if (clickedImage.classList.contains('choosed')) {
    isChanged = false;
  } else {
    productImageSmall.forEach(image => {
      image.classList.remove('choosed');
    });

    clickedImage.classList.add('choosed');
  }

  const productImage = document.querySelectorAll('.product__image');
  productImage.forEach(image => {
    if (image.classList.contains(`product__image--${imageNumber}`)) {
      image.classList.remove('hidden');
    } else {
      image.classList.add('hidden');
    }
  });

  if (isChanged === true) {
    productEllipses.forEach(ellipse => {
      ellipse.classList.toggle('product__ellipse--gray');
    });
  }
}