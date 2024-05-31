CREATE TABLE partners (
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    name text NOT NULL,
    email text NOT NULL,
    password text NOT NULL,
    color varchar(7) NOT NULL,
    picture text,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

INSERT INTO partners (id, name, email, password, color) VALUES ('fa3ddf2e-18cf-471b-aee2-31342b7ed6de', 'John Doe', 'john.doe@gmail.com', '$2a$10$vHj.K/5DJkuWrVcUno4wCutgbLIWSXcum64virHaXx89WmmUi.j4W', '#E11501');
INSERT INTO partners (id, name, email, password, color) VALUES ('417efff9-5890-4a1b-babb-0764b88d4312', 'Sarah Downey', 'sarahd@gmail.com', '$2a$10$vHj.K/5DJkuWrVcUno4wCutgbLIWSXcum64virHaXx89WmmUi.j4W', '#E07905');
INSERT INTO partners (id, name, email, password, color) VALUES ('93f5387e-6a7e-4829-986f-34e3d02f5062', 'Clement Marigold', 'clem1@gmail.com', '$2a$10$vHj.K/5DJkuWrVcUno4wCutgbLIWSXcum64virHaXx89WmmUi.j4W', '#0121E1');


CREATE TABLE projects (
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    name text NOT NULL,
    description text,
    code text NOT NULL,
    created_by uuid NOT NULL REFERENCES partners(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE links (
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    project_id uuid NOT NULL REFERENCES projects(id),
    name text NOT NULL,
    url text NOT NULL
);

INSERT INTO projects (id, name, description, code, created_by) VALUES (
    '71f2d38f-3cdf-42aa-ad0d-dac2d5bd794e',
    'Aurora',
    'Project management tool for software developers',
    'AUR',
    'fa3ddf2e-18cf-471b-aee2-31342b7ed6de'
);

INSERT INTO projects (id, name, description, code, created_by) VALUES (
    'b52a4df2-2a2a-498b-90e0-ecc984d8a021',
    'Pathfinder',
    'Pathfinding algorithm visualiser',
    'PFDR',
    'fa3ddf2e-18cf-471b-aee2-31342b7ed6de'
);

INSERT INTO projects (id, name, description, code, created_by) VALUES (
    '34f9f34a-b599-40bd-85c7-f4fdf22418e2',
    'LifeSim',
    'Simple ecosystem simulation',
    'LS',
    '417efff9-5890-4a1b-babb-0764b88d4312'
);

INSERT INTO links (id, project_id, name, url) VALUES (
    'b69d45da-79ff-47c3-860a-70e91738d685',
    '71f2d38f-3cdf-42aa-ad0d-dac2d5bd794e',
    'Github',
    'https://github.com/facebook/react'
);

CREATE TABLE teams (
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    project_id uuid NOT NULL REFERENCES projects(id),
    created_by uuid NOT NULL REFERENCES partners(id)
);

CREATE TABLE team_partner(
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    team_id uuid NOT NULL REFERENCES teams(id),
    partner_id uuid NOT NULL REFERENCES partners(id)
);

INSERT INTO teams (id, project_id, created_by) VALUES ('44ee0b17-6ad7-4059-87ab-b2c6fb5b5fef', '71f2d38f-3cdf-42aa-ad0d-dac2d5bd794e', 'fa3ddf2e-18cf-471b-aee2-31342b7ed6de');
INSERT INTO team_partner (id, team_id, partner_id) VALUES ('d41647e3-4e88-4f0f-b3cb-eedd0697335a', '44ee0b17-6ad7-4059-87ab-b2c6fb5b5fef', '417efff9-5890-4a1b-babb-0764b88d4312');
INSERT INTO team_partner (id, team_id, partner_id) VALUES ('08d20cf5-7f92-4c09-832b-deb6f7697c24', '44ee0b17-6ad7-4059-87ab-b2c6fb5b5fef', '93f5387e-6a7e-4829-986f-34e3d02f5062');

CREATE TABLE requests (
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    sender_id uuid NOT NULL,
    recipient_id uuid NOT NULL,
    team_id uuid NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

INSERT INTO requests (id, sender_id, recipient_id, team_id) VALUES ('be7b86d0-9096-46bd-b921-3d69a83751c8', 'fa3ddf2e-18cf-471b-aee2-31342b7ed6de', '417efff9-5890-4a1b-babb-0764b88d4312', '44ee0b17-6ad7-4059-87ab-b2c6fb5b5fef');

CREATE TABLE story_state (
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    name text NOT NULL,
    code text NOT NULL
);

INSERT INTO story_state (id, name, code) VALUES ('1bb861b4-368a-4657-bb59-8e114d7476bf', 'To do', 'to-do');
INSERT INTO story_state (id, name, code) VALUES ('67fa489d-19d1-4092-8c91-4120c57453cc', 'In progress', 'in-progress');
INSERT INTO story_state (id, name, code) VALUES ('9936e382-8bae-431f-919f-9de6b3087dd7', 'In review', 'in-review');
INSERT INTO story_state (id, name, code) VALUES ('15ef65ae-db56-4df1-97e3-0ce0f7cb675d', 'In testing', 'in-testing');
INSERT INTO story_state (id, name, code) VALUES ('562aec2e-8995-4ab8-aa70-358fc7664a95', 'Business review', 'bs-review');
INSERT INTO story_state (id, name, code) VALUES ('2dcaeec5-81d6-445c-9b31-fc526612c5ae', 'Blocked', 'blocked');
INSERT INTO story_state (id, name, code) VALUES ('b7b04545-1777-432e-b7f8-628fdfa7801c', 'Done', 'done');

CREATE TABLE project_board (
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    project_id uuid NOT NULL REFERENCES projects(id),
    state_id uuid NOT NULL REFERENCES story_state(id)
);

INSERT INTO project_board (id, project_id, state_id) VALUES ('fe84b2d8-4531-4f5d-974c-f13afef55381', '71f2d38f-3cdf-42aa-ad0d-dac2d5bd794e', '1bb861b4-368a-4657-bb59-8e114d7476bf');
INSERT INTO project_board (id, project_id, state_id) VALUES ('bd689149-3ae5-42c0-87ae-2e9a89c73597', '71f2d38f-3cdf-42aa-ad0d-dac2d5bd794e', '67fa489d-19d1-4092-8c91-4120c57453cc');
INSERT INTO project_board (id, project_id, state_id) VALUES ('969a8b13-6236-4c7a-bc4c-66a2a17b31b3', '71f2d38f-3cdf-42aa-ad0d-dac2d5bd794e', '9936e382-8bae-431f-919f-9de6b3087dd7');
INSERT INTO project_board (id, project_id, state_id) VALUES ('92c4b160-1cfc-4d56-a430-4355985c0a21', '71f2d38f-3cdf-42aa-ad0d-dac2d5bd794e', '15ef65ae-db56-4df1-97e3-0ce0f7cb675d');
INSERT INTO project_board (id, project_id, state_id) VALUES ('2937fe70-800e-461d-aa80-cd0c576d55a5', '71f2d38f-3cdf-42aa-ad0d-dac2d5bd794e', '562aec2e-8995-4ab8-aa70-358fc7664a95');
INSERT INTO project_board (id, project_id, state_id) VALUES ('b0a76f78-ef60-4274-b437-31b406793861', '71f2d38f-3cdf-42aa-ad0d-dac2d5bd794e', '2dcaeec5-81d6-445c-9b31-fc526612c5ae');
INSERT INTO project_board (id, project_id, state_id) VALUES ('1120a005-8d34-4ca6-b731-b389e43815d9', '71f2d38f-3cdf-42aa-ad0d-dac2d5bd794e', 'b7b04545-1777-432e-b7f8-628fdfa7801c');

CREATE TABLE story_type (
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    name text NOT NULL,
    code text NOT NULL
);

INSERT INTO story_type (id, name, code) VALUES ('aba66ad5-5762-4a10-8853-aab6a40d300a', 'Story', 'story');
INSERT INTO story_type (id, name, code) VALUES ('bfd991ec-6b4f-413b-b077-00523157e1e0', 'Bug', 'bug');

CREATE TABLE stories (
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    name text NOT NULL,
    description text DEFAULT '',
    code text NOT NULL,
    in_board BOOLEAN NOT NULL DEFAULT FALSE,
    project_id uuid NOT NULL REFERENCES projects(id),
    state_id uuid NOT NULL REFERENCES story_state(id),
    type_id uuid NOT NULL REFERENCES story_type(id),
    created_by uuid NOT NULL REFERENCES partners(id),
    assigned_to uuid REFERENCES partners(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

INSERT INTO stories (id, name, description, code, in_board, project_id, state_id, type_id, created_by, assigned_to) VALUES ('c6ab8968-5dec-454f-97dd-aeb78925b41f', 'Test story', 'This is a description of first story', 'AUR-1', TRUE, '71f2d38f-3cdf-42aa-ad0d-dac2d5bd794e', '1bb861b4-368a-4657-bb59-8e114d7476bf', 'aba66ad5-5762-4a10-8853-aab6a40d300a', 'fa3ddf2e-18cf-471b-aee2-31342b7ed6de', 'fa3ddf2e-18cf-471b-aee2-31342b7ed6de');
INSERT INTO stories (id, name, description, code, in_board, project_id, state_id, type_id, created_by, assigned_to) VALUES ('faaf5b45-766c-44c0-a249-3007d84197c0', 'Test bug', 'This is a description of first bug', 'AUR-2', TRUE, '71f2d38f-3cdf-42aa-ad0d-dac2d5bd794e', 'b7b04545-1777-432e-b7f8-628fdfa7801c', 'bfd991ec-6b4f-413b-b077-00523157e1e0', 'fa3ddf2e-18cf-471b-aee2-31342b7ed6de', NULL);

CREATE TABLE substories (
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    parent_id uuid NOT NULL REFERENCES stories(id),
    name text NOT NULL,
    description text DEFAULT '',
    code text NOT NULL,
    state_id uuid NOT NULL REFERENCES story_state(id),
    type_id uuid NOT NULL REFERENCES story_type(id),
    created_by uuid NOT NULL REFERENCES partners(id),
    assigned_to uuid REFERENCES partners(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

INSERT INTO substories (id, parent_id, name, description, code, state_id, type_id, created_by, assigned_to) VALUES (
    'f88d309c-b6c6-4f4d-84a2-e08db82eb6cd',
    'c6ab8968-5dec-454f-97dd-aeb78925b41f',
    'Test substory',
    'This is a description of first substory',
    'AUR-3',
    '1bb861b4-368a-4657-bb59-8e114d7476bf',
    'aba66ad5-5762-4a10-8853-aab6a40d300a',
    'fa3ddf2e-18cf-471b-aee2-31342b7ed6de',
    'fa3ddf2e-18cf-471b-aee2-31342b7ed6de'
    );

INSERT INTO substories (id, parent_id, name, description, code, state_id, type_id, created_by, assigned_to) VALUES (
    'b4163a93-b4d6-4c51-a3a2-13a292dc28b1',
    'c6ab8968-5dec-454f-97dd-aeb78925b41f',
    'Test substory 2',
    'This is a description of the second substory',
    'AUR-4',
    '1bb861b4-368a-4657-bb59-8e114d7476bf',
    'aba66ad5-5762-4a10-8853-aab6a40d300a',
    'fa3ddf2e-18cf-471b-aee2-31342b7ed6de',
    NULL
    );