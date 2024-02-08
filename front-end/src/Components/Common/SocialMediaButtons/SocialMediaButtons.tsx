import { FC } from "react";
import "./SocialMediaButtons.scss";

import InstagramIcon from "./assets/icons/instagram.svg";
import TelegramIcon from "./assets/icons/telegram.svg";
import ViberIcon from "./assets/icons/viber.svg";
import MessageIcon from "./assets/icons/message.svg";

const ContactsIcons = {
  pressed: false,

  toggle() {
    const contactIcons = document.querySelectorAll(".feedback-button__icon");
    Array.from(contactIcons).forEach((elem) =>
      elem.classList.toggle("feedback-button__icon--show")
    );

    ContactsIcons.pressed = !ContactsIcons.pressed;
  },

  handleDocumentClick: function (event: MouseEvent) {
    const menuIconBtn = document.querySelector(
      ".feedback-button__icon-message"
    ) as Element;
    const targetElement = event.target;

    if (
      this.pressed &&
      targetElement !== menuIconBtn &&
      !menuIconBtn.contains(targetElement as Node)
    ) {
      this.toggle();
    }
  },
};

export const SocialMediaButtons: FC = () => {
  document.addEventListener(
    "click",
    ContactsIcons.handleDocumentClick.bind(ContactsIcons)
  );

  return (
    <div className="feedback-button">
      <div className="feedback-button__icons">
        <a href="#">
          <img
            src={InstagramIcon}
            className="feedback-button__icon feedback-button__icon--instagram"
            alt="Instagram"
          />
        </a>

        <a href="#">
          <img
            src={ViberIcon}
            className="feedback-button__icon feedback-button__icon--viber"
            alt="Viber"
          />
        </a>

        <a href="#">
          <img
            src={TelegramIcon}
            className="feedback-button__icon feedback-button__icon--telegram"
            alt="Telegram"
          />
        </a>
      </div>
      <img
        src={MessageIcon}
        className="feedback-button__icon-message"
        role="button"
        alt="icon-open"
        onClick={ContactsIcons.toggle}
      />
    </div>
  );
};
