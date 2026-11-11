package com.cappucare.backend.config;

import com.cappucare.backend.model.Categoria;
import com.cappucare.backend.model.Producto;
import com.cappucare.backend.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class DataLoader {

    @Bean
    @Profile("!test")
    CommandLineRunner init(ProductoRepository productoRepository) {
        return args -> {
            if (productoRepository.count() == 0) {
                productoRepository.saveAll(cargarInsumosBase());
            }
        };
    }

    private java.util.List<Producto> cargarInsumosBase() {
        java.util.List<Producto> insumos = new java.util.ArrayList<>();

        insumos.add(new Producto(
                "MED-101", "Mascarilla Quirúrgica N95",
                "Mascarilla de alta filtración uso clínico",
                Categoria.PROTECCION, 45, "Caja (50 un)", 50, "Estante A-1", "Disponible", 1L));
        insumos.add(new Producto(
                "MED-102", "Alcohol Gel 70% 500ml",
                "Antiséptico de manos con dispensador",
                Categoria.DESCARTABLES, 120, "Frasco 500ml", 30, "Estante B-2", "Disponible", 1L));
        insumos.add(new Producto(
                "MED-103", "Gasa Esterilizada 10x10cm",
                "Gasa quirúrgica estéril",
                Categoria.CURACION, 12, "Caja (100 un)", 25, "Estante C-1", "Disponible", 1L));
        insumos.add(new Producto(
                "MED-104", "Termómetro Infrarrojo Digital",
                "Termómetro sin contacto LCD",
                Categoria.EQUIPAMIENTO, 8, "Unidad", 5, "Vitrina M-1", "Disponible", 1L));
        insumos.add(new Producto(
                "MED-105", "Jeringa Desechable 5ml",
                "Jeringa estéril de un solo uso con aguja integrada",
                Categoria.DESCARTABLES, 200, "Unidad", 100, "Estante A-2", "Disponible", 1L));
        insumos.add(new Producto(
                "MED-106", "Aguja Hipodérmica 21G",
                "Aguja hipodérmica estéril calibre 21",
                Categoria.DESCARTABLES, 150, "Caja (100 un)", 50, "Estante A-3", "Disponible", 1L));
        insumos.add(new Producto(
                "MED-107", "Solución Salina 0.9% 500ml",
                "Suero fisiológico para hidratación intravenosa",
                Categoria.MEDICAMENTOS, 60, "Frasco 500ml", 20, "Estante B-1", "Disponible", 1L));
        insumos.add(new Producto(
                "MED-108", "Vendaje Elástico 10cm",
                "Vendaje elástico para inmovilización y compresión",
                Categoria.CURACION, 30, "Unidad", 15, "Estante C-2", "Disponible", 1L));
        insumos.add(new Producto(
                "MED-109", "Guantes de Nitrile Talla M",
                "Guantes de examen de nitrilo sin polvo, talla mediana",
                Categoria.PROTECCION, 80, "Caja (100 un)", 40, "Estante A-1", "Disponible", 1L));
        insumos.add(new Producto(
                "MED-110", "Desinfectante de Superficies 1L",
                "Desinfectante de amplio espectro para superficies clínicas",
                Categoria.DESCARTABLES, 40, "Frasco 1L", 10, "Estante B-3", "Disponible", 1L));
        insumos.add(new Producto(
                "MED-111", "Catéter Venoso 22G",
                "Catéter intravenoso estéril calibre 22 con puerto de inyección",
                Categoria.DESCARTABLES, 90, "Unidad", 30, "Estante A-4", "Disponible", 1L));
        insumos.add(new Producto(
                "MED-112", "Tensiómetro Digital",
                "Tensiómetro de brazo digital con pantalla LCD",
                Categoria.EQUIPAMIENTO, 6, "Unidad", 3, "Vitrina M-2", "Disponible", 1L));
        insumos.add(new Producto(
                "MED-113", "Oxímetro de Pulso",
                "Oxímetro de dedo para medición de saturación de oxígeno",
                Categoria.EQUIPAMIENTO, 10, "Unidad", 5, "Vitrina M-3", "Disponible", 1L));
        insumos.add(new Producto(
                "MED-114", "Electrocardiógrafo Portátil",
                "Equipo portátil para registro de electrocardiograma",
                Categoria.EQUIPAMIENTO, 3, "Unidad", 2, "Vitrina M-1", "Disponible", 1L));
        insumos.add(new Producto(
                "MED-115", "Suero Glucosado 5% 500ml",
                "Suero intravenoso con dextrosa al 5%",
                Categoria.MEDICAMENTOS, 50, "Frasco 500ml", 15, "Estante B-1", "Disponible", 1L));
        insumos.add(new Producto(
                "MED-116", "Vendaje Gasífero",
                "Rollo de venda gasífera estéril para curaciones",
                Categoria.CURACION, 40, "Rollo", 20, "Estante C-1", "Disponible", 1L));
        insumos.add(new Producto(
                "MED-117", "Mascarilla Quirúrgica Triple Capa",
                "Mascarilla desechable de tres capas de filtración",
                Categoria.PROTECCION, 60, "Caja (50 un)", 30, "Estante A-1", "Disponible", 1L));
        insumos.add(new Producto(
                "MED-118", "Batín Desechable",
                "Batín de protección desechable talla única",
                Categoria.PROTECCION, 40, "Unidad", 20, "Estante A-2", "Disponible", 1L));
        insumos.add(new Producto(
                "MED-119", "Gafas de Protección",
                "Gafas de seguridad con protección lateral",
                Categoria.PROTECCION, 25, "Unidad", 10, "Estante A-3", "Disponible", 1L));
        insumos.add(new Producto(
                "MED-120", "Termómetro Digital Oral",
                "Termómetro digital de punta flexible para uso oral",
                Categoria.EQUIPAMIENTO, 15, "Unidad", 8, "Vitrina M-2", "Disponible", 1L));
        insumos.add(new Producto(
                "MED-121", "Ibuprofeno 400mg",
                "Antiinflamatorio y analgésico, caja de 30 comprimidos",
                Categoria.MEDICAMENTOS, 200, "Caja (30 un)", 60, "Estante D-1", "Disponible", 1L));
        insumos.add(new Producto(
                "MED-122", "Paracetamol 500mg",
                "Analgésico y antipirético, caja de 30 comprimidos",
                Categoria.MEDICAMENTOS, 180, "Caja (30 un)", 50, "Estante D-1", "Disponible", 1L));
        insumos.add(new Producto(
                "MED-123", "Vendaje Adhesivo Varias Tallas",
                "Tiritas adhesivas estériles de distintas medidas",
                Categoria.CURACION, 50, "Caja (100 un)", 30, "Estante C-2", "Disponible", 1L));
        insumos.add(new Producto(
                "MED-124", "Agua Oxigenada 120ml",
                "Solución oxidadora para limpieza de heridas",
                Categoria.DESCARTABLES, 30, "Frasco 120ml", 10, "Estante B-2", "Disponible", 1L));
        insumos.add(new Producto(
                "MED-125", "Sutura Nylon 3-0",
                "Sutura no absorbible de nylon con aguja de 3/8",
                Categoria.DESCARTABLES, 20, "Caja (12 un)", 8, "Estante A-5", "Disponible", 1L));
        insumos.add(new Producto(
                "MED-126", "Estetoscopio Littman",
                "Estetoscopio cardiológico de alta sensibilidad",
                Categoria.EQUIPAMIENTO, 8, "Unidad", 4, "Vitrina M-3", "Disponible", 1L));
        insumos.add(new Producto(
                "MED-127", "Botiquín de Primeros Auxilios",
                "Kit completo de primeros auxilios para consultorio",
                Categoria.EQUIPAMIENTO, 12, "Unidad", 5, "Almacén General", "Disponible", 1L));
        insumos.add(new Producto(
                "MED-128", "Suero Oftálmico Lubricante",
                "Lágrimas artificiales para sequedad ocular",
                Categoria.MEDICAMENTOS, 25, "Frasco 15ml", 10, "Estante D-2", "Disponible", 1L));

        return insumos;
    }
}
