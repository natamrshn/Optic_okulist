import React from "react";
import { Link } from "react-router-dom";
import { useSelector } from "react-redux";
import { GrFormClose } from "react-icons/gr";

import "./ShoppingCart.scss";
import ShoppingCartProduct from "./ShoppingCartProduct";
import { selectCart } from "../../redux/cart/cartSlice";

type ShoppingCartProps = {
    setIsCartOpened: React.Dispatch<React.SetStateAction<boolean>>,
    // ref: React.LegacyRef<HTMLUListElement> | undefined,
};

export const glassesProducts = [
    {
        name: "Elegant Square Glasses",
        price: 79.99,
        description:
            "These elegant square glasses feature a modern design that effortlessly combines style and sophistication. The angular frames complement a variety of face shapes, making them a versatile addition to any wardrobe. Crafted with high-quality materials, these glasses offer both durability and comfort for all-day wear. Whether you're heading to the office or a special event, these glasses will elevate your look with a touch of refined elegance.",
        imgUrl: "https://assets.glasses.com/is/image/Glasses/805289270102__STD__shad__qt.png?impolicy=GL_parameters_transp_clone1440",
        amount: 1,
    },
    {
        name: "Retro Round Frames",
        price: 64.5,
        description:
            "Embrace a classic look with these retro-inspired round frames. The timeless design exudes a sense of vintage charm while maintaining a modern edge. The lightweight construction ensures a comfortable fit, making them suitable for all-day wear. These frames are a versatile accessory that can effortlessly transition from casual outings to more formal occasions. Elevate your style with these round frames that capture the essence of both past and present fashion trends.",
        imgUrl: "https://assets.glasses.com/is/image/Glasses/805289270102__STD__shad__qt.png?impolicy=GL_parameters_transp_clone1440",
        amount: 1,
    },
    {
        name: "Sporty Wraparound Sunglasses",
        price: 89.99,
        description:
            "Designed for the active lifestyle, these sleek wraparound sunglasses offer both style and functionality. The wraparound design provides excellent coverage and protection, making them perfect for outdoor activities. The high-quality lenses offer UV protection, ensuring your eyes are shielded from harmful rays. The lightweight yet durable frame construction ensures a comfortable fit during even the most demanding activities. Whether you're hitting the trails or simply enjoying a day in the sun, these sunglasses are a must-have accessory for any outdoor enthusiast.",
        imgUrl: "https://assets.glasses.com/is/image/Glasses/805289270102__STD__shad__qt.png?impolicy=GL_parameters_transp_clone1440",
        amount: 1,
    },
    {
        name: "Elegant Square Glasses",
        price: 79.99,
        description:
            "These elegant square glasses feature a modern design that effortlessly combines style and sophistication. The angular frames complement a variety of face shapes, making them a versatile addition to any wardrobe. Crafted with high-quality materials, these glasses offer both durability and comfort for all-day wear. Whether you're heading to the office or a special event, these glasses will elevate your look with a touch of refined elegance.",
        imgUrl: "https://assets.glasses.com/is/image/Glasses/805289270102__STD__shad__qt.png?impolicy=GL_parameters_transp_clone1440",
        amount: 1,
    },
    {
        name: "Retro Round Frames",
        price: 64.5,
        description:
            "Embrace a classic look with these retro-inspired round frames. The timeless design exudes a sense of vintage charm while maintaining a modern edge. The lightweight construction ensures a comfortable fit, making them suitable for all-day wear. These frames are a versatile accessory that can effortlessly transition from casual outings to more formal occasions. Elevate your style with these round frames that capture the essence of both past and present fashion trends.",
        imgUrl: "https://assets.glasses.com/is/image/Glasses/805289270102__STD__shad__qt.png?impolicy=GL_parameters_transp_clone1440",
        amount: 1,
    },
    {
        name: "Sporty Wraparound Sunglasses",
        price: 89.99,
        description:
            "Designed for the active lifestyle, these sleek wraparound sunglasses offer both style and functionality. The wraparound design provides excellent coverage and protection, making them perfect for outdoor activities. The high-quality lenses offer UV protection, ensuring your eyes are shielded from harmful rays. The lightweight yet durable frame construction ensures a comfortable fit during even the most demanding activities. Whether you're hitting the trails or simply enjoying a day in the sun, these sunglasses are a must-have accessory for any outdoor enthusiast.",
        imgUrl: "https://assets.glasses.com/is/image/Glasses/805289270102__STD__shad__qt.png?impolicy=GL_parameters_transp_clone1440",
        amount: 1,
    },
    {
        name: "Elegant Square Glasses",
        price: 79.99,
        description:
            "These elegant square glasses feature a modern design that effortlessly combines style and sophistication. The angular frames complement a variety of face shapes, making them a versatile addition to any wardrobe. Crafted with high-quality materials, these glasses offer both durability and comfort for all-day wear. Whether you're heading to the office or a special event, these glasses will elevate your look with a touch of refined elegance.",
        imgUrl: "https://assets.glasses.com/is/image/Glasses/805289270102__STD__shad__qt.png?impolicy=GL_parameters_transp_clone1440",
        amount: 1,
    },
    {
        name: "Retro Round Frames",
        price: 64.5,
        description:
            "Embrace a classic look with these retro-inspired round frames. The timeless design exudes a sense of vintage charm while maintaining a modern edge. The lightweight construction ensures a comfortable fit, making them suitable for all-day wear. These frames are a versatile accessory that can effortlessly transition from casual outings to more formal occasions. Elevate your style with these round frames that capture the essence of both past and present fashion trends.",
        imgUrl: "https://assets.glasses.com/is/image/Glasses/805289270102__STD__shad__qt.png?impolicy=GL_parameters_transp_clone1440",
        amount: 1,
    },
    {
        name: "Sporty Wraparound Sunglasses",
        price: 89.99,
        description:
            "Designed for the active lifestyle, these sleek wraparound sunglasses offer both style and functionality. The wraparound design provides excellent coverage and protection, making them perfect for outdoor activities. The high-quality lenses offer UV protection, ensuring your eyes are shielded from harmful rays. The lightweight yet durable frame construction ensures a comfortable fit during even the most demanding activities. Whether you're hitting the trails or simply enjoying a day in the sun, these sunglasses are a must-have accessory for any outdoor enthusiast.",
        imgUrl: "https://assets.glasses.com/is/image/Glasses/805289270102__STD__shad__qt.png?impolicy=GL_parameters_transp_clone1440",
        amount: 1,
    },
    {
        name: "Elegant Square Glasses",
        price: 79.99,
        description:
            "These elegant square glasses feature a modern design that effortlessly combines style and sophistication. The angular frames complement a variety of face shapes, making them a versatile addition to any wardrobe. Crafted with high-quality materials, these glasses offer both durability and comfort for all-day wear. Whether you're heading to the office or a special event, these glasses will elevate your look with a touch of refined elegance.",
        imgUrl: "https://assets.glasses.com/is/image/Glasses/805289270102__STD__shad__qt.png?impolicy=GL_parameters_transp_clone1440",
        amount: 1,
    },
    {
        name: "Retro Round Frames",
        price: 64.5,
        description:
            "Embrace a classic look with these retro-inspired round frames. The timeless design exudes a sense of vintage charm while maintaining a modern edge. The lightweight construction ensures a comfortable fit, making them suitable for all-day wear. These frames are a versatile accessory that can effortlessly transition from casual outings to more formal occasions. Elevate your style with these round frames that capture the essence of both past and present fashion trends.",
        imgUrl: "https://assets.glasses.com/is/image/Glasses/805289270102__STD__shad__qt.png?impolicy=GL_parameters_transp_clone1440",
        amount: 1,
    },
    {
        name: "Sporty Wraparound Sunglasses",
        price: 89.99,
        description:
            "Designed for the active lifestyle, these sleek wraparound sunglasses offer both style and functionality. The wraparound design provides excellent coverage and protection, making them perfect for outdoor activities. The high-quality lenses offer UV protection, ensuring your eyes are shielded from harmful rays. The lightweight yet durable frame construction ensures a comfortable fit during even the most demanding activities. Whether you're hitting the trails or simply enjoying a day in the sun, these sunglasses are a must-have accessory for any outdoor enthusiast.",
        imgUrl: "https://assets.glasses.com/is/image/Glasses/805289270102__STD__shad__qt.png?impolicy=GL_parameters_transp_clone1440",
        amount: 1,
    },
    {
        name: "Chic Cat Eye Glasses",
        price: 74.95,
        description:
            "Elevate your style with these chic cat eye glasses that add a touch of glamour to any outfit. The upswept frames create a flattering and feminine silhouette that complements a wide range of face shapes. The high-quality materials ensure both durability and comfort, making them suitable for all-day wear. Whether you're dressing up for a special occasion or adding a touch of sophistication to your everyday look, these cat eye glasses are the perfect accessory to make a statement with elegance and grace.",
        imgUrl: "https://assets.glasses.com/is/image/Glasses/805289270102__STD__shad__qt.png?impolicy=GL_parameters_transp_clone1440",
        amount: 1,
    },
    {
        name: "Aviator Sunglasses",
        price: 95.0,
        description:
            "Experience the timeless appeal of classic aviator sunglasses. With their iconic teardrop-shaped lenses and metal frames, these sunglasses exude an effortlessly cool and versatile style. The high-quality lenses provide excellent clarity and UV protection, ensuring optimal eye comfort in various lighting conditions. Whether you're on a sunny beach or navigating city streets, these aviator sunglasses are a reliable and fashionable choice. Elevate your eyewear collection with this enduring design that transcends trends and complements any wardrobe.",
        imgUrl: "https://assets.glasses.com/is/image/Glasses/805289270102__STD__shad__qt.png?impolicy=GL_parameters_transp_clone1440",
        amount: 1,
    },
];

export const prodsWithIdsIds = glassesProducts.map((product, id) => ({
    ...product,
    id: id.toString(),
}));

const ShoppingCart: React.FC<ShoppingCartProps> = ({ setIsCartOpened }) => {
    const products = useSelector(selectCart);

    return (
        <>
            <ul className="cart">
                <div className="cart-header">
                    <h3>Products in your cart:</h3>
                    <button
                        className="cart-close"
                        onClick={() =>
                            setIsCartOpened((prevState) => !prevState)
                        }
                    >
                        <GrFormClose size={30} />
                    </button>
                </div>
                {products.map((product, index) => (
                    <ShoppingCartProduct key={product.name + index} product={product} />
                ))}

                <Link to="/cart">Go to your cart</Link>
            </ul>
        </>
    );
};

export default ShoppingCart;
