import { Meta, StoryObj } from "@storybook/react";
import { Button, ButtonProps } from "./Button";
import "./Button.scss";

const meta: Meta<ButtonProps> = {
  title: "Components/UI/Button",
  component: Button,
};

export default meta;
type Story = StoryObj<ButtonProps>;

export const Primary: Story = {
  args: {
    children: "Primary Button",
    callback: () => console.log("Primary button"),
  },
};

export const Secondary: Story = {
  args: {
    children: "Secondary Button",
    callback: () => console.log("Secondary button"),
    isSecondary: true,
  },
};

export const Disabled: Story = {
  args: {
    children: "Disabled Button",
    callback: () => {},
    disabled: true,
  },
};

export const DisabledSecondary: Story = {
  args: {
    children: "Disabled Secondary Button",
    callback: () => {},
    disabled: true,
    isSecondary: true,
  },
};
