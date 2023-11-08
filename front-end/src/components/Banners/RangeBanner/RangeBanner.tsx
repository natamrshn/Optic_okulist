import Container from "../../ui/Container/Container";
import "./RangeBanner.scss";
import { Link } from "react-router-dom";

const RangeBanner = () => {
    const items = [
        {
            id: "glasses",
            title: "Окуляри",
            description: "Найкращі окуляри в світі",
            imgSrc: "https://img.freepik.com/free-photo/eyeglasses-wear_1203-2604.jpg?w=1060&t=st=1699347681~exp=1699348281~hmac=2883f8b7db95bbfc43f37297c5737d4b70a2863d1f7031539bd58794fe4a3c63",
            alt: "Окуляри",
        },
        {
            id: "sunglasses",
            title: "Сонезахисні окуляри",
            description: "Захист від сонця і стильний вигляд",
            imgSrc: "https://img.freepik.com/free-photo/sunglasses_1203-8703.jpg?w=1060&t=st=1699348515~exp=1699349115~hmac=41be174d55eafa0c81fd2550d1890d76470e05108923761e8a8d0e9b5008b9d9",
            alt: "Сонезахисні окуляри",
        },
        {
            id: "contact-lenses",
            title: "Контактні лінзи",
            description: "Зручність і чудовий зір",
            imgSrc: "https://img.freepik.com/free-photo/high-angle-of-pair-of-contact-lenses-with-case-and-tweezers_23-2148429639.jpg?w=1060&t=st=1699349627~exp=1699350227~hmac=a8d99d5fbb4a6297071e28cb3b0daa3e3e412d730af43c2e0a3b2b179696ed46",
            alt: "Контактні лінзи",
        },
        {
            id: "accessories",
            title: "Аксесуари",
            description: "Додаткові аксесуари для вашого комфорту",
            imgSrc: "https://img.freepik.com/free-photo/eyeglasses-wear_1203-2608.jpg?w=1060&t=st=1699348625~exp=1699349225~hmac=3fbd2e889089f05e4bfd7500a362a3555ad73ae7098b62d5c8e0790f24b755c6",
            alt: "Аксесуари",
        },
    ];

    return (
        <aside className="banner">
            <Container>
                <h2>Наш ассортимент</h2>
                <ul className="list">
                    {items.map((item) => {
                        return (
                            <li key={item.id} className="item">
                                <Link to={`/${item.id}`} className="item-content">
                                    <img className="item-image" src={item.imgSrc} alt={item.alt} />
                                    <h3 className="item-title">{item.title}</h3>
                                    {/* <p className="item-description">{item.description}</p> */}
                                </Link>
                            </li>
                        );
                    })}
                </ul>
            </Container>
        </aside>
    );
};

export default RangeBanner;
