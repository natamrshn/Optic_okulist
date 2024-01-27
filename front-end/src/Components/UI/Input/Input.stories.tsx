import React, {useState} from "react";
import {Meta, Story } from "@storybook/react";
import { Input, InputProps } from "./Input";
import './Input.scss';

const meta: Meta<InputProps> = {
  title: 'Components/UI/Input',
  component: Input,
  argTypes: {
    type: {
      defaultValue: 'text',
      options: ['text', 'password', 'email', 'tel', 'number'],
      control: {
        type: 'radio'
      },
    },
    value: { type: "string" }
  }
}

export default meta;
type StoryProps = Partial<InputProps>;

export const Default: Story<StoryProps> = (args) => {
  const [value, setValue] = useState<string>('');
  const [error, setError] = useState('');

  const inputOnchangeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    setValue(e.target.value);
  }

  return <Input type={args.type} value={value} setValue={inputOnchangeHandler} error={!!error} />
}

export const Disabled: Story<StoryProps> = (args) => {
  const [value, setValue] = useState<string>('');
  const [error, setError] = useState('');

  const inputOnchangeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    setValue(e.target.value);
  }

  return <Input type={args.type} value={value} setValue={inputOnchangeHandler} error={!!error} disabled={true}/>
}

export const WithError: Story<StoryProps> = (args) => {
  const [value, setValue] = useState<string>('');
  const [error, setError] = useState('There is an error');

  const inputOnchangeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    setValue(e.target.value);
  }

  return <Input type={args.type} value={value} setValue={inputOnchangeHandler} error={!!error} />
}