INSERT INTO public.cidade (id_cidade, ds_nome, ds_nome_estado, ds_regiao, ds_sigla_estado)
VALUES (1100015, 'Alta Floresta D''Oeste', 'Rondônia', 'Norte', 'RO');

INSERT INTO public.usuario (dt_nascimento, fg_ativo, dt_alteracao, dt_cadastro, id_cidade, id_usuario, num_predial,
                            ds_telefone, ds_salt, ds_bairro, ds_complemento, ds_email, ds_endereco, ds_nome,
                            ds_token_recuperacao, ds_senha, ds_cpf)
VALUES ('1993-05-11', 'T', '2024-09-06 17:47:55.823394', '2024-09-06 17:47:55.823366', 1100015, 1, 234, '46956775434',
        '06b411c1-7343-4862-9872-2cf5ebf0fb79', 'Centro', 'Casa', 'admin@teste.com', 'Teste', 'Teste', null,
        '717b4b529841919da7a249174f3522fd46aa1cadead53bb8856edb4ac910464230bce9ef4bf402776870d4a421105a00dbe998f4c0021bf31f69e3ba7df80b5f',
        '22672227012');

alter sequence seq_usuario restart with 2;