package com.cappucare.backend.config;

import com.cappucare.backend.model.Categoria;
import com.cappucare.backend.model.Movimiento;
import com.cappucare.backend.model.Producto;
import com.cappucare.backend.model.TipoMovimiento;
import com.cappucare.backend.repository.MovimientoRepository;
import com.cappucare.backend.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class DataLoader {

    @Bean
    @Profile("!test")
    CommandLineRunner init(ProductoRepository productoRepository,
                          MovimientoRepository movimientoRepository) {
        return args -> {
            sincronizarCatalogoOficial(productoRepository);
            if (movimientoRepository.count() == 0) {
                movimientoRepository.saveAll(cargarMovimientosBase());
            }
        };
    }

    private void sincronizarCatalogoOficial(ProductoRepository productoRepository) {
        List<Producto> oficiales = cargarInsumosBase();
        Set<String> idsOficiales = oficiales.stream().map(Producto::getId).collect(Collectors.toSet());

        // 1. Eliminar cualquier producto que NO pertenezca al catálogo oficial de Cappucare
        List<Producto> existentes = productoRepository.findAll();
        for (Producto p : existentes) {
            if (!idsOficiales.contains(p.getId())) {
                productoRepository.delete(p);
            }
        }

        // 2. Guardar productos oficiales sin duplicar
        for (Producto p : oficiales) {
            if (!productoRepository.existsById(p.getId())) {
                productoRepository.save(p);
            }
        }
    }

    private List<Movimiento> cargarMovimientosBase() {
        List<Movimiento> movs = new ArrayList<>();
        long ahora = System.currentTimeMillis();

        Movimiento m1 = new Movimiento();
        m1.setProductoId("CAP-201");
        m1.setProductoNombre("Cinturón Masajeador Menstrual Térmico");
        m1.setTipo(TipoMovimiento.INGRESO);
        m1.setCantidad(10);
        m1.setMotivo("Recepción Almacén Cappucare");
        m1.setFechaHora(ahora - 1000 * 60 * 30);
        m1.setUsuarioResponsable("Juan Pérez");
        m1.setSincronizado(true);
        movs.add(m1);

        Movimiento m2 = new Movimiento();
        m2.setProductoId("CAP-204");
        m2.setProductoNombre("Bolsa de Frío Instantáneo");
        m2.setTipo(TipoMovimiento.SALIDA);
        m2.setCantidad(5);
        m2.setMotivo("Despacho Cliente");
        m2.setFechaHora(ahora - 1000 * 60 * 15);
        m2.setUsuarioResponsable("Paz Morales");
        m2.setSincronizado(true);
        movs.add(m2);

        Movimiento m3 = new Movimiento();
        m3.setProductoId("CAP-208");
        m3.setProductoNombre("Glucómetro Digital");
        m3.setTipo(TipoMovimiento.INGRESO);
        m3.setCantidad(4);
        m3.setMotivo("Reposición Vitrina M-1");
        m3.setFechaHora(ahora - 1000 * 60 * 5);
        m3.setUsuarioResponsable("Carlos López");
        m3.setSincronizado(true);
        movs.add(m3);

        return movs;
    }

    private List<Producto> cargarInsumosBase() {
        List<Producto> insumos = new ArrayList<>();

        // Catálogo Oficial Cappucare (30 Insumos Oficiales)
        insumos.add(new Producto(
                "CAP-201", "Cinturón Masajeador Menstrual Térmico",
                "Dispositivo inalámbrico de termoterapia y masaje vibratorio para alivio del dolor.",
                Categoria.PROTECCION, 25, "Unidad (Blanco/Rosa)", 10, "Vitrina Cappucare M-1", "Disponible", 1L));

        insumos.add(new Producto(
                "CAP-202", "Cinturón Masajeador Lumbar",
                "Faja de compresión y masaje térmico lumbar para recuperación articular y dolor de espalda.",
                Categoria.PROTECCION, 18, "Unidad", 8, "Vitrina Cappucare M-2", "Disponible", 1L));

        insumos.add(new Producto(
                "CAP-203", "Banda Smart Articular",
                "Banda inteligente de soporte y termoterapia para articulaciones de rodilla y codo.",
                Categoria.EQUIPAMIENTO, 15, "Unidad", 5, "Vitrina Cappucare M-3", "Disponible", 1L));

        insumos.add(new Producto(
                "CAP-204", "Bolsa de Frío Instantáneo",
                "Bolsa térmica de activación por presión para primeros auxilios y desinflamación.",
                Categoria.DESCARTABLES, 150, "Unidad", 30, "Estante A-1", "Disponible", 1L));

        insumos.add(new Producto(
                "CAP-205", "Adhesivos para Heridas (Sutura Cutánea)",
                "Tiras adhesivas estériles para cierre de heridas leves sin sutura quirúrgica.",
                Categoria.CURACION, 80, "Caja (10 un)", 25, "Estante C-1", "Disponible", 1L));

        insumos.add(new Producto(
                "CAP-206", "Faja Lumbar Deportiva",
                "Faja ortopédica de soporte lumbar para actividad física y corrección postural.",
                Categoria.PROTECCION, 30, "Unidad", 10, "Vitrina Cappucare M-2", "Disponible", 1L));

        insumos.add(new Producto(
                "CAP-207", "Gasa Parafinada Estéril",
                "Gasa estéril impregnada en parafina para curación no adherente de quemaduras y heridas.",
                Categoria.CURACION, 100, "Caja (10 un)", 20, "Estante C-1", "Disponible", 1L));

        insumos.add(new Producto(
                "CAP-208", "Glucómetro Digital",
                "Dispositivo médico de medición rápida de glucosa en sangre con pantalla LCD.",
                Categoria.EQUIPAMIENTO, 12, "Unidad", 5, "Vitrina M-1", "Disponible", 1L));

        insumos.add(new Producto(
                "CAP-209", "Guantes Desechables sin Polvo",
                "Guantes de examen desechables de alta resistencia sin polvo.",
                Categoria.DESCARTABLES, 200, "Caja (100 un)", 50, "Estante A-1", "Disponible", 1L));

        insumos.add(new Producto(
                "CAP-210", "Masajeador de Cuello y Hombros",
                "Masajeador Shiatsu térmico ergonómico para tensión cervical y muscular.",
                Categoria.EQUIPAMIENTO, 15, "Unidad", 5, "Vitrina Cappucare M-1", "Disponible", 1L));

        insumos.add(new Producto(
                "CAP-211", "Masajeador Térmico de Pies",
                "Masajeador de pies con hidromasaje y calor infrarrojo para relajación y circulación.",
                Categoria.EQUIPAMIENTO, 10, "Unidad", 4, "Vitrina Cappucare M-2", "Disponible", 1L));

        insumos.add(new Producto(
                "CAP-212", "Nebulizador Portátil",
                "Nebulizador ultrasónico portátil de malla para terapia respiratoria continua.",
                Categoria.EQUIPAMIENTO, 14, "Unidad", 5, "Vitrina M-2", "Disponible", 1L));

        insumos.add(new Producto(
                "CAP-213", "Parche Curita Impermeable",
                "Apósitos adhesivos estériles resistentes al agua y humedad.",
                Categoria.CURACION, 120, "Caja (50 un)", 30, "Estante C-2", "Disponible", 1L));

        insumos.add(new Producto(
                "CAP-214", "Parche Curita Infantil",
                "Apósitos adhesivos hipoalergénicos con diseños infantiles.",
                Categoria.CURACION, 100, "Caja (50 un)", 25, "Estante C-2", "Disponible", 1L));

        insumos.add(new Producto(
                "CAP-215", "Pelotas Anti Estrés",
                "Set de esferas de resistencia progresiva para kinesiología y fortalecimiento de mano.",
                Categoria.EQUIPAMIENTO, 60, "Set (3 un)", 15, "Almacén General", "Disponible", 1L));

        insumos.add(new Producto(
                "CAP-216", "Pistola de Masaje Muscular",
                "Pistola de percusión muscular de alta velocidad con cabezales intercambiables.",
                Categoria.EQUIPAMIENTO, 8, "Unidad", 3, "Vitrina Cappucare M-3", "Disponible", 1L));

        insumos.add(new Producto(
                "CAP-217", "Rodillera Térmica",
                "Rodillera ergonómica de compresión con termoterapia integrada.",
                Categoria.PROTECCION, 20, "Unidad", 8, "Vitrina Cappucare M-3", "Disponible", 1L));

        insumos.add(new Producto(
                "CAP-218", "Rollo Adhesivo No Tejido 5cm",
                "Cinta médica hipoalergénica de fijación respirable no tejida 5cm x 10m.",
                Categoria.CURACION, 75, "Rollo", 20, "Estante C-1", "Disponible", 1L));

        insumos.add(new Producto(
                "CAP-219", "Rollo Adhesivo Transparente 5cm",
                "Cinta adhesiva médica transparente de polietileno microperforado 5cm.",
                Categoria.CURACION, 75, "Rollo", 20, "Estante C-1", "Disponible", 1L));

        insumos.add(new Producto(
                "CAP-220", "Saturómetro Adulto",
                "Oxímetro de pulso digital de dedo con pantalla OLED para adultos.",
                Categoria.EQUIPAMIENTO, 25, "Unidad", 8, "Vitrina M-3", "Disponible", 1L));

        insumos.add(new Producto(
                "CAP-221", "Saturómetro Pediátrico",
                "Oxímetro de pulso de precisión pediátrico con diseño ergonómico infantil.",
                Categoria.EQUIPAMIENTO, 15, "Unidad", 5, "Vitrina M-3", "Disponible", 1L));

        insumos.add(new Producto(
                "CAP-222", "Tape Kinesiológica",
                "Cinta neuromuscular elástica de algodón para vendaje funcional 5cm x 5m.",
                Categoria.CURACION, 90, "Rollo", 25, "Estante C-2", "Disponible", 1L));

        insumos.add(new Producto(
                "CAP-223", "TENS Digital Recargable",
                "Electroestimulador muscular y neurológico TENS/EMS recargable por USB.",
                Categoria.EQUIPAMIENTO, 12, "Unidad", 4, "Vitrina M-1", "Disponible", 1L));

        insumos.add(new Producto(
                "CAP-224", "Termómetro Digital",
                "Termómetro clínico digital de lectura rápida punta flexible.",
                Categoria.EQUIPAMIENTO, 40, "Unidad", 15, "Vitrina M-2", "Disponible", 1L));

        insumos.add(new Producto(
                "CAP-225", "Termómetro Digital Infantil",
                "Termómetro digital pediátrico ultra rápido sin contacto.",
                Categoria.EQUIPAMIENTO, 30, "Unidad", 10, "Vitrina M-2", "Disponible", 1L));

        insumos.add(new Producto(
                "CAP-226", "Toallas con Alcohol",
                "Toallitas antisépticas individuales impregnadas en alcohol 70%.",
                Categoria.DESCARTABLES, 180, "Caja (100 un)", 40, "Estante A-2", "Disponible", 1L));

        insumos.add(new Producto(
                "CAP-227", "Tobillera Recovery Pro",
                "Soporte ortopédico para tobillo con bandas de estabilización lateral.",
                Categoria.PROTECCION, 16, "Unidad", 6, "Vitrina Cappucare M-3", "Disponible", 1L));

        insumos.add(new Producto(
                "CAP-228", "Toma Presión Digital",
                "Tensiómetro automático de brazo con memoria e indicador de arritmia.",
                Categoria.EQUIPAMIENTO, 14, "Unidad", 5, "Vitrina M-2", "Disponible", 1L));

        insumos.add(new Producto(
                "CAP-229", "Venda Elástica 10cm x 4,5m",
                "Venda de compresión elástica estirable 10cm x 4.5m.",
                Categoria.CURACION, 60, "Unidad", 20, "Estante C-2", "Disponible", 1L));

        insumos.add(new Producto(
                "CAP-230", "Venda Elástica con Gancho 5cm x 4,5m",
                "Venda elástica de fijación rápida con ganchos metálicos de ajuste 5cm.",
                Categoria.CURACION, 70, "Unidad", 20, "Estante C-2", "Disponible", 1L));

        return insumos;
    }
}
